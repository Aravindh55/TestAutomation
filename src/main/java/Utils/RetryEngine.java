package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryEngine implements IRetryAnalyzer {
	
	
	int count = 0;
	int maxCount = 3;

	@Override
	public boolean retry(ITestResult result) {
		
		if (!result.isSuccess()) {
			if (count<maxCount) {
				count++;
				result.setStatus(ITestResult.SUCCESS);
				return true;
			} else {
				result.setStatus(ITestResult.FAILURE);
			}
		}else {
			result.setStatus(ITestResult.SUCCESS);
		}
		
		return false;
	}

}
