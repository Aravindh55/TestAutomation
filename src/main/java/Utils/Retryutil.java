package Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.annotations.ITestAnnotation;

public class Retryutil implements IAnnotationTransformer, ITestListener {
	public void transform(
		      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		   annotation.setRetryAnalyzer(RetryEngine.class);
		  }
}
