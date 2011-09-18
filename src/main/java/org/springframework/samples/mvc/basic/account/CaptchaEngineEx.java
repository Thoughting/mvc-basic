package org.springframework.samples.mvc.basic.account;

import java.awt.Color;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * 自定义的验证码生成引擎
 * @author ghost
 *
 */
public class CaptchaEngineEx extends ListImageCaptchaEngine {

	/**
	 * 生成验证码的具体方法
	 */
	@Override
	protected void buildInitialFactories() {

		//验证码的最小长度
		Integer minAcceptedWordLength = new Integer(4);
		//验证码的最大长度
		Integer maxAcceptedWordLength = new Integer(4);

		//验证码图片的高度宽度设定
		Integer imageHeight = new Integer(36);
		Integer imageWidth = new Integer(80);

		//验证码中显示的字体大小
		Integer minFontSize = new Integer(18);
		Integer maxFontSize = new Integer(20);

		//随机字符生成
		//abcdefghijklmnopqrstuvwxyz
		WordGenerator wordGenerator = new RandomWordGenerator("0123456789");

		//背景颜色随机生成
		BackgroundGenerator backgroundGenerator = new GradientBackgroundGenerator(imageWidth, imageHeight, Color.white,
				Color.white);

		//字体随机生成
		FontGenerator fontGenerator = new RandomFontGenerator(minFontSize, maxFontSize);

		//验证码的颜色
		SingleColorGenerator singleColor = new SingleColorGenerator(Color.blue);

		BaffleTextDecorator baffleTextDecorator = new BaffleTextDecorator(1, Color.white);
		//可以创建多个
		//LineTextDecorator lineTextDecorator = new LineTextDecorator(1, Color.blue);
		//TextDecorator[] textDecorator = new TextDecorator[2];
		//textDecorator[0] = lineTextDecorator;
		TextDecorator[] textDecorator = new TextDecorator[1];
		textDecorator[0] = baffleTextDecorator;

		TextPaster textPaster = new DecoratedRandomTextPaster(minAcceptedWordLength, maxAcceptedWordLength,
				singleColor, textDecorator);
		//生成图片输出
		WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);

		addFactory(new GimpyFactory(wordGenerator, wordToImage));
	}

}
