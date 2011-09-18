package org.springframework.samples.mvc.basic.account;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

/*
 * 自定义的验证码生成器
 */
public class CaptchaService extends DefaultManageableImageCaptchaService {

	public CaptchaService() {
		super();
	}

	/**
	 * @param minSeconds
	 * @param maxStoreSize 最大缓存大小
	 * @param loadBefore
	 */
	public CaptchaService(int minSeconds, int maxStoreSize, int loadBefore) {
		super(minSeconds, maxStoreSize, loadBefore);
	}

	public CaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine, int minSeconds, int maxStroreSize,
			int loadBefore) {
		super(captchaStore, captchaEngine, minSeconds, maxStroreSize, loadBefore);
	}

	/**
	 * 重写验证方法 
	 * 掩盖异常 当出现异常时判定为验证失败
	 */
	@Override
	public Boolean validateResponseForID(String ID, Object response) {
		Boolean isHuman;

		try {
			isHuman = super.validateResponseForID(ID, response);
		} catch (Exception e) {
			isHuman = false;
		}

		return isHuman;
	}
}
