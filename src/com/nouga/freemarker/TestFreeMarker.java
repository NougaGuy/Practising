package com.nouga.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author Nouga
 * Date : 2017/8/12
 * Declaration : All Rights Reserved !
 * Use Version ： 2.3.23
 */
public class TestFreeMarker {

	/**
	 * Freemarker生成的页面不是jsp，是以后缀为.ftl的静态页面
	 */
	@Test
	public void testFreemarker() throws Exception {
		//1.创建一个模版文件
		Configuration configuration = new Configuration(Configuration.getVersion());
		//2.创建一个Configuration对象
		//3.设置模版所在的路径
		configuration.setDirectoryForTemplateLoading(new File("D:/work/Java/Workspace/Practising/src/com/nouga/freemarker"));
		//4.设置模版的字符集，一般是UTF-8
		configuration.setDefaultEncoding("utf-8");
		//5.使用Configuration加载一个模版文件，需要指定模版文件的文件名
		Template template = configuration.getTemplate("testFK.ftl");
		//6.创建数据集，可以是pojo也可以是map，推荐使用map
		Map data = new HashMap<>();
		List<Student> stuList = new ArrayList();
		stuList.add(new Student(1, "小米", 11, "北京昌平回龙观"));
		stuList.add(new Student(2, "小米2", 12, "北京昌平回龙观"));
		stuList.add(new Student(3, "小米3", 13, "北京昌平回龙观"));
		stuList.add(new Student(4, "小米4", 14, "北京昌平回龙观"));
		stuList.add(new Student(5, "小米5", 15, "北京昌平回龙观"));
		stuList.add(new Student(6, "小米6", 16, "北京昌平回龙观"));
		stuList.add(new Student(7, "小米7", 17, "北京昌平回龙观"));
		data.put("date", new Date());
		data.put("hello", "HelloFreemarker");
		data.put("stuList", stuList);
		//日期类型的处理
		//7.创建一个writer对象，指定输出文件的路径及文件名
		Writer writer = new FileWriter(new File("D:/work/Test/TestOutPut/FK/Hello.txt"));
		//8.使用模版对象process方法输出文件。
		template.process(data, writer);
		//9.关闭流
		writer.close();
	}
}
