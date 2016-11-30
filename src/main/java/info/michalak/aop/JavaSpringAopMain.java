package info.michalak.aop;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import info.michalak.aop.services.ShapeServices;

public class JavaSpringAopMain {

	
	
	
	public static void main(String[] args) {
		ApplicationContext  applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		
		ShapeServices shapeServices = applicationContext.getBean("shapeServices",ShapeServices.class );
		System.out.println(shapeServices.getCircle().getName());
	}
}
