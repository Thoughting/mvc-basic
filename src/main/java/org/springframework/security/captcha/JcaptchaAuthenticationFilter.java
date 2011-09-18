package org.springframework.security.captcha;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.filter.GenericFilterBean;

import com.octo.captcha.service.image.ImageCaptchaService;

public class JcaptchaAuthenticationFilter extends GenericFilterBean {
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_captcha";
	public static final String SPRING_SECURITY_DEFAULT_PROCESSES_URL = "/j_spring_security_check";
	private boolean postOnly = true;
	private String captchaParameter = "j_captcha";
	private ImageCaptchaService imageCaptchaService;
	private String filterProcessesUrl = "/j_spring_security_check";
	private String authenticationFailureUrl;
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	public void attemptAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws AuthenticationException, IOException, ServletException {
		if ((this.postOnly) && (!request.getMethod().equals("POST"))) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		if (!getImageCaptchaService().validateResponseForID(request.getSession().getId(), obtainCaptcha(request))
				.booleanValue()) {
			response.sendRedirect(request.getContextPath() + getAuthenticationFailureUrl());
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		int pathParamIndex = uri.indexOf(';');

		if (pathParamIndex > 0) {
			uri = uri.substring(0, pathParamIndex);
		}
		if ("".equals(request.getContextPath())) {
			return uri.endsWith(getFilterProcessesUrl());
		}
		return uri.endsWith(request.getContextPath() + getFilterProcessesUrl());
	}

	protected String obtainCaptcha(HttpServletRequest request) {
		return request.getParameter(this.captchaParameter);
	}

	/*
		protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException failed) throws IOException, ServletException {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Authentication request failed: " + failed.toString());
				this.logger.debug("Updated SecurityContextHolder to contain null Authentication");
				this.logger.debug("Delegating to authentication failure handler" + this.failureHandler);
			}
			this.failureHandler.onAuthenticationFailure(request, response, failed);
		}
	*/

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (!requiresAuthentication(request, response)) {
			chain.doFilter(request, response);
			return;
		}
		if ((this.postOnly) && (!request.getMethod().equals("POST"))) {
			response.sendRedirect(request.getContextPath() + getAuthenticationFailureUrl());
			return;
		}
		try {
			if (!getImageCaptchaService().validateResponseForID(request.getSession().getId(), obtainCaptcha(request))
					.booleanValue()) {
				//response.sendRedirect(request.getContextPath() + getAuthenticationFailureUrl());
				AuthenticationException exception = new AuthenticationServiceException(this.messages.getMessage(
						"JcaptchaAuthenticationFilter.invalidCode",
						"The verification code was invalid or has timed out, please try again."));//"验证码输入错误或已经超时失效，请重新输入。");
				if (authenticationFailureUrl == null) {
					logger.debug("No failure URL set, sending 401 Unauthorized error");
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: "
							+ exception.getMessage());
				} else {
					HttpSession session = request.getSession(false);
					if (session != null) {
						request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
					}
					response.sendRedirect(request.getContextPath() + getAuthenticationFailureUrl());
				}
				return;
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + getAuthenticationFailureUrl());
			return;
		}
		chain.doFilter(request, response);
	}

	public ImageCaptchaService getImageCaptchaService() {
		return this.imageCaptchaService;
	}

	public void setImageCaptchaService(ImageCaptchaService imageCaptchaService) {
		this.imageCaptchaService = imageCaptchaService;
	}

	public String getFilterProcessesUrl() {
		return this.filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	public String getAuthenticationFailureUrl() {
		return this.authenticationFailureUrl;
	}

	public void setAuthenticationFailureUrl(String authenticationFailureUrl) {
		this.authenticationFailureUrl = authenticationFailureUrl;
	}
}