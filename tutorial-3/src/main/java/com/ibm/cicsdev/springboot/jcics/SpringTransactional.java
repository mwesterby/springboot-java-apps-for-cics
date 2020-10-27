package com.ibm.cicsdev.springboot.jcics;



import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ibm.cics.server.CicsConditionException;
import com.ibm.cics.server.TSQ;

/**
 * This component uses @Transactional to manage transactions in
 * a container oriented manner.
 */
@Component
public class SpringTransactional 
{
    /**
     * Write an input string to a CICS TSQ using the @Transactional annotation. 
     * If the input text contains 'rollback' then generate a RuntimeException
     * which triggers rollback.
     * 
     * @param text
     * @return status message
     * @throws CicsConditionException 
     */
    @Transactional (rollbackFor=Exception.class)
    public String writeTSQ(String text) throws CicsConditionException 
    {
        // Create JCICS representation of TSQ object
        TSQ tsq = new TSQ();
        tsq.setName(CicsJavaLibertySpringbootJcicsApplication.TSQNAME);

        // Write it to the TSQ
        tsq.writeString(text);

        // If the string contains 'rollback' then throw an exception to trigger rollback
        if (text.contains("rollback")) 
        {
            throw new RuntimeException("SpringTransactional.writeTSQ(): Rollback request was detected"); 
        }    

        return text;
    }
}
