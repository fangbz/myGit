package bz.ognl;

import java.util.ArrayList;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OgnlTest 
{

	public static void main(String[] args) throws OgnlException 
	{
		
		Dog dog2 = new Dog();
		dog2.setName("xiaoqiang");
		
		Person person = new Person();
		person.setName("zhangsan");
		person.setDog(dog2);
		
		Dog dog = new Dog();
		dog.setName("wangcai");
		
		
		
		//初始化一个ognlcontext对象
		OgnlContext context = new OgnlContext();
		context.put("person", person);
		context.put("dog", dog);
		
		// 设置根对象
		context.setRoot(person);
		
		Object object = Ognl.parseExpression("name");
		System.out.println(object);
		
		//Ognl.getValue(object, context, person);
		Object object2 = Ognl.getValue(object, context, context.getRoot());
		System.out.println(object2);
		
		System.out.println("==================================================");
		
		Object object3 = Ognl.parseExpression("#person.name");
		System.out.println(object3);
		Object object4 = Ognl.getValue(object3, context, context.getRoot());
		System.out.println(object4);
		
		System.out.println("==================================================");
		
		Object object5 = Ognl.parseExpression("#dog.name");
		System.out.println(object5);
		Object object6 = Ognl.getValue(object5, context, context.getRoot());
		System.out.println(object6);
		
		System.out.println("==================================================");
		
		//直接从根对象寻找,dog是根对象 person的属性
		Object object7 = Ognl.parseExpression("dog.name");
		System.out.println(object5);
		Object object8 = Ognl.getValue(object7, context, context.getRoot());
		System.out.println(object8);
		
		System.out.println("==================================================");
		
		//可以直接调用对象的其他方法,注意调用方法必须加 ()
		Object object9 = Ognl.parseExpression("name.toUpperCase()");
		//Object object9 = Ognl.parseExpression("name.toUpperCase().length()");
		Object object10 = Ognl.getValue(object9, context, context.getRoot());
		System.out.println(object10);
		
		System.out.println("==================================================");
		
       //调用静态方法   @package.classname@methodname(parameter)
		Object object11 = Ognl.parseExpression("@java.lang.Integer@toBinaryString(10)");
		Object object12 = Ognl.getValue(object11, context, context.getRoot());
		System.out.println(object12);
		
		System.out.println("==================================================");
		
		 //调用Math类的静态方法 Math唯一 一个不需要写完整包名类名的类
		Object object13 = Ognl.parseExpression("@@min(10,2)");
		Object object14 = Ognl.getValue(object13, context, context.getRoot());
		System.out.println(object14);
		
		System.out.println("==================================================");
		
		 //调用静态属性
		Object object15 = Ognl.parseExpression("@@E");
		Object object16 = Ognl.getValue(object15, context, context.getRoot());
		System.out.println(object16);
		
		System.out.println("==================================================");
		
		 //可以直接生成一个对象
		Object object17 = Ognl.parseExpression("new java.util.ArrayList()");
		System.out.println(object17);
		Object object18 = Ognl.getValue(object17, context, context.getRoot());
		System.out.println(object18);
		
		System.out.println("==================================================");
		
		//还可以这样写,注意 Ognl.getValue 和上诉参数对比
		Object object19 = Ognl.getValue("new java.util.ArrayList()", context, context.getRoot());
		System.out.println(object19);
		
		System.out.println("==================================================");
		
		//直接生成一个集合对象,注意这里使用的是花括号,在ognl中集合和数组是一样的
		Object object20 = Ognl.getValue("{'a','b','c','d'}", context, context.getRoot());
		System.out.println(object20);
		//访问集合的元素,通过下标获取
		Object object21 = Ognl.getValue("{'a','b','c','d'}[2]", context, context.getRoot());
		System.out.println(object21);
		
		System.out.println("==================================================");
		
		dog.setFriends(new String[]{"zz","vv","xx"} );
		Object object22 = Ognl.getValue("#dog.friends[1]", context, context.getRoot());
		System.out.println(object22);
		
		System.out.println("==================================================");
		
		List list = new ArrayList();
		list.add("hello");
		list.add("world");
		list.add("hello,world");
		
		context.put("list", list);
		Object object23 = Ognl.getValue("#list", context, context.getRoot());
		System.out.println(object23);
		Object object24 = Ognl.getValue("#list[2]", context, context.getRoot());
		System.out.println(object24);
		
		System.out.println("==================================================");
		
		Object object25 = Ognl.getValue("#{'key1':'value1','key2':'value2','key3':'value3'}", context, context.getRoot());
		System.out.println(object25);
		
		Object object26 = Ognl.getValue("#{'key1':'value1','key2':'value2','key3':'value3'}['key2']", context, context.getRoot());
		System.out.println(object26);
		
		System.out.println("==================================================");
		
		// 过滤  主要有下面三种情况
		List<Person> persons = new ArrayList<Person>();
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		p1.setName("zhangsan");
		p2.setName("lisi");
		p3.setName("wangwu");
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		context.put("persons", persons);
		
		//  过滤-->1) 找出名字长度大于4的对象的集合    #this  代表当前遍历的集合对象
		Object object27 = Ognl.getValue("#persons.{? #this.name.length()>4}", context, context.getRoot());
		System.out.println(object27);
		
		Object object28 = Ognl.getValue("#persons.{? #this.name.length()>4}.size()", context, context.getRoot());
		// 伪属性的本质原因在于集合当中的很多方法并不符合javaBean的命名规范
		//Object object28 = Ognl.getValue("#persons.{? #this.name.length()>4}.isEmpty()", context, context.getRoot());
		//Object object28 = Ognl.getValue("#persons.{? #this.name.length()>4}.isEmpty", context, context.getRoot()); // 伪属性,记不住最好直接写方法
		// Object object28 = Ognl.getValue("#persons.{? #this.name.length()>4}.size", context, context.getRoot());  //  size不加括号,可以看做是伪属性
		System.out.println(object28);
		
		System.out.println("==================================================");
		//过滤-->2)  找到集合中第一个符合条件的元素,虽然只是一个元素,但是还是以集合形式表示,只是只有一个元素
		Object object29 = Ognl.getValue("#persons.{^ #this.name.length()>4 }", context, context.getRoot());
		System.out.println(object29);
		Object object30 = Ognl.getValue("#persons.{^ #this.name.length()>4 }[0].name", context, context.getRoot());
		System.out.println(object30);
		
		//过滤-->3) 找到集合中最后一个符合条件的元素,虽然只是一个元素,但是还是以集合形式表示,只是只有一个元素
		Object object31 =  Ognl.getValue("#persons.{$ #this.name.length()>4 }[0].name", context, context.getRoot());
		System.out.println(object31);
		
		
		System.out.println("==================================================");
		
		// 投影 取列操作
		Object object32 = Ognl.getValue("#persons.{name }", context, context.getRoot());
		System.out.println(object32);
		
		System.out.println("==================================================");
		
		//如果名字长度大于5直接返回,如果名字长度不大于5返回 Hello,World
		Object object33 = Ognl.getValue("#persons.{name.length()<=5?'Hello,World':#this.name}", context, context.getRoot());
		System.out.println(object33);
		
	}
}
