/**
 * 
 */
package org.mps.exceptions;

/**
 *
 *Nov 24, 2023
 *@Author Akshay Shadakshari
 *@version
 *@since 
 *
 */
@SuppressWarnings("serial")
public class BrowserInvocationFailedException extends FrameworkException{

	/**
	 * @param message
	 */
	public BrowserInvocationFailedException(String message) {
		super(message);
	}
	
	public BrowserInvocationFailedException(String message, Throwable cause) {
		super(message,cause);
	}

}
