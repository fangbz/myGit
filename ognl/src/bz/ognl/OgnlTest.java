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
		
		
		
		//��ʼ��һ��ognlcontext����
		OgnlContext context = new OgnlContext();
		context.put("person", person);
		context.put("dog", dog);
		
		// ���ø�����
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
		
		//ֱ�ӴӸ�����Ѱ��,dog�Ǹ����� person������
		Object object7 = Ognl.parseExpression("dog.name");
		System.out.println(object5);
		Object object8 = Ognl.getValue(object7, context, context.getRoot());
		System.out.println(object8);
		
		System.out.println("==================================================");
		
		//����ֱ�ӵ��ö������������,ע����÷�������� ()
		Object object9 = Ognl.parseExpression("name.toUpperCase()");
		//Object object9 = Ognl.parseExpression("name.toUpperCase().length()");
		Object object10 = Ognl.getValue(object9, context, context.getRoot());
		System.out.println(object10);
		
		System.out.println("==================================================");
		
       //���þ�̬����   @package.classname@methodname(parameter)
		Object object11 = Ognl.parseExpression("@java.lang.Integer@toBinaryString(10)");
		Object object12 = Ognl.getValue(object11, context, context.getRoot());
		System.out.println(object12);
		
		System.out.println("==================================================");
		
		 //����Math��ľ�̬���� MathΨһ һ������Ҫд����������������
		Object object13 = Ognl.parseExpression("@@min(10,2)");
		Object object14 = Ognl.getValue(object13, context, context.getRoot());
		System.out.println(object14);
		
		System.out.println("==================================================");
		
		 //���þ�̬����
		Object object15 = Ognl.parseExpression("@@E");
		Object object16 = Ognl.getValue(object15, context, context.getRoot());
		System.out.println(object16);
		
		System.out.println("==================================================");
		
		 //����ֱ������һ������
		Object object17 = Ognl.parseExpression("new java.util.ArrayList()");
		System.out.println(object17);
		Object object18 = Ognl.getValue(object17, context, context.getRoot());
		System.out.println(object18);
		
		System.out.println("==================================================");
		
		//����������д,ע�� Ognl.getValue �����߲����Ա�
		Object object19 = Ognl.getValue("new java.util.ArrayList()", context, context.getRoot());
		System.out.println(object19);
		
		System.out.println("==================================================");
		
		//ֱ������һ�����϶���,ע������ʹ�õ��ǻ�����,��ognl�м��Ϻ�������һ����
		Object object20 = Ognl.getValue("{'a','b','c','d'}", context, context.getRoot());
		System.out.println(object20);
		//���ʼ��ϵ�Ԫ��,ͨ���±��ȡ
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
		
		// ����  ��Ҫ�������������
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
		
		//  ����-->1) �ҳ����ֳ��ȴ���4�Ķ���ļ���    #this  ����ǰ�����ļ��϶���
		Object object27 = Ognl.getValue("#persons.{? #this.name.length()>4}", context, context.getRoot());
		System.out.println(object27);
		
		Object object28 = Ognl.getValue("#persons.{? #this.name.length()>4}.size()", context, context.getRoot());
		// α���Եı���ԭ�����ڼ��ϵ��еĺܶ෽����������javaBean�������淶
		//Object object28 = Ognl.getValue("#persons.{? #this.name.length()>4}.isEmpty()", context, context.getRoot());
		//Object object28 = Ognl.getValue("#persons.{? #this.name.length()>4}.isEmpty", context, context.getRoot()); // α����,�ǲ�ס���ֱ��д����
		// Object object28 = Ognl.getValue("#persons.{? #this.name.length()>4}.size", context, context.getRoot());  //  size��������,���Կ�����α����
		System.out.println(object28);
		
		System.out.println("==================================================");
		//����-->2)  �ҵ������е�һ������������Ԫ��,��Ȼֻ��һ��Ԫ��,���ǻ����Լ�����ʽ��ʾ,ֻ��ֻ��һ��Ԫ��
		Object object29 = Ognl.getValue("#persons.{^ #this.name.length()>4 }", context, context.getRoot());
		System.out.println(object29);
		Object object30 = Ognl.getValue("#persons.{^ #this.name.length()>4 }[0].name", context, context.getRoot());
		System.out.println(object30);
		
		//����-->3) �ҵ����������һ������������Ԫ��,��Ȼֻ��һ��Ԫ��,���ǻ����Լ�����ʽ��ʾ,ֻ��ֻ��һ��Ԫ��
		Object object31 =  Ognl.getValue("#persons.{$ #this.name.length()>4 }[0].name", context, context.getRoot());
		System.out.println(object31);
		
		
		System.out.println("==================================================");
		
		// ͶӰ ȡ�в���
		Object object32 = Ognl.getValue("#persons.{name }", context, context.getRoot());
		System.out.println(object32);
		
		System.out.println("==================================================");
		
		//������ֳ��ȴ���5ֱ�ӷ���,������ֳ��Ȳ�����5���� Hello,World
		Object object33 = Ognl.getValue("#persons.{name.length()<=5?'Hello,World':#this.name}", context, context.getRoot());
		System.out.println(object33);
		
	}
}
