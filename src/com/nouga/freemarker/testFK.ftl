<html>
	<head>
		<title>Freemarker测试用法</title>
	</head>
	<body>
		${hello}   --  Freemarker标识符，在测试中，我用map封装了一个名为Hello的数据，把里面的内容带了出来。
		
		测试#list   #if
        <table border="1">
        	<tr>
        		<th>序号</th>
        		<th>学号</th>
        		<th>姓名</th>
        		<th>年龄</th>
        		<th>家庭住址</th>
        	</tr>
		    <#list stuList as stu>
		    <#if stu_index%2==0>
		    <tr bgcolor="red">
		    <#else>
		    <tr bgcolor="yellow">
		    </#if>
		    	<td>${stu_index}</td>
		    	<td>${stu.id}</td>
		    	<td>${stu.name}</td>
		    	<td>${stu.age}</td>
		    	<td>${stu.address}</td>
		    	</tr>
		    </#list>
		</table>
		<br>Null值的处理     :  ${val!}</br>
		
		<br>
		Date类型处理：  
		${date?date}
		${date?time}
		${date?datetime}
		${date?string("yyyy-MM-dd HH:mm:ss")}
		</br>
		
		<br>
		使用If判断null值
		<#if val??>
		val有值
		<#else>
		val值为null
		</#if>
		</br>
		
		<br>
		include标签测试: <#include "hello.ftl">
		</br>
	</body>
</html>