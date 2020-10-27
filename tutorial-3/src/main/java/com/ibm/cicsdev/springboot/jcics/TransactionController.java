package com.ibm.cicsdev.springboot.jcics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController 
{   
    @GetMapping("/")
    public String index() 
    {
        return "Greetings from com.ibm.cicsdev.springboot.transaction servlet";       
    }
    
    @Autowired SpringTransactional transactional;

    /**
     * Demonstrate Transactional annotation commit
     * @return status message
     */
    @GetMapping({"/transactionalCommit", "/transactionalcommit"})
    public String transactionalCommit() 
    {
        // Commit a TSQ write using Spring's @Transactional annotation
        try 
        {
            return this.transactional.writeTSQ("hello CICS from transactionalCommit()");
        }
        catch(Exception e)
        {                           
            e.printStackTrace();
            return "transactionalCommit: exception: "  + e.getMessage() + ". Check dfhjvmerr for further details.";
        }       
    }
    
    /**
     * Demonstrate Transactional annotation rollback
     * @return status message
     */
    @GetMapping({"/transactionalRollback", "/transactionalrollback"})
    public String transactionalRollback()
    {
        // Attempt to write to a TSQ
        // ...but when the string 'rollback' is detected in the input, we 
        // intentionally throw a runtime exception which the @Transactional 
        // annotation has been qualified to detect and trigger a rollback.
        try 
        {
            return this.transactional.writeTSQ("rollback from transactionalRollback()");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "transactionalRollback: exception: "  + e.getMessage() + ". Rollback triggered - see dfhjvmerr for further details.";
        }       
    }
    
    @Autowired SpringTransactionTemplate springTemplateTran;

    /**
     * Spring Template managed transaction commit web request
     * @return message
     */
    @GetMapping({"/STcommit", "/stcommit"})
    public String springTemplateCommit() 
    {
        return this.springTemplateTran.writeTSQ("hello CICS from springTemplateCommit()");
    }


    /**
     * Spring Template managed transaction rollback web request
     * @return message
     */
    @GetMapping({"/STrollback", "/strollback"})
    public String springTemplateRollback() 
    {
        return this.springTemplateTran.writeTSQ("rollback from springTemplateRollback()");
    }
}
