package core;

/**
 *  <tt>RecSysLibException</tt> is the superclass of those exceptions that can be thrown 
 *  during the runtime of the <tt>RecSysLib</tt>.
 *  @version 1.0 2012-4-26
 *  @author Tan Chang
 * 	@since JDK 1.7
 */
public class RecSysLibException extends RuntimeException {	

	private static final long serialVersionUID = 7943581685650043801L;
	
	public RecSysLibException(String message){
		super(message);
	}

}
