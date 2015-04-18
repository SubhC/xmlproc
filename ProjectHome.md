This methodology helps you to externalize the agile business processes. With the change in the business process, there is absolute no need to change the implementation code. This can be done using changes in the XML. The XML holds the business logic semantics which can get executed at any time you want.

Please, note "It is not a API collections, it is a methodology"

Just go to the test package and check how to execute the business logic.........

---

LOGIC:

---



&lt;example3&gt;


> 

&lt;for start="12" end="24" step="1"&gt;


> 

&lt;print value="Value of Start " /&gt;


> 

&lt;print value="start" /&gt;


> 

&lt;if lvalue="start" operator="GT" rvalue="20"&gt;


> > 

&lt;print value="Start is Grater than 20" /&gt;



> 

&lt;/if&gt;


> 

&lt;/for&gt;




&lt;/example3&gt;



---

HOW TO EXECUTE:

---

/**org.eshark.xmlprog**/
public class TestExecutorService
{

> /
    * @author       :: Subhasish Chattopadhyay
    * Introduced on :: Mar 18, 2008 12:48:20 PM
    * Purpose       ::
    * 
    * @version      ::
    * @param args
    * 
> public static void main(String[.md](.md) args)throws XMLProgException
> {
> > ClassCache.getClassCache().loadFromXML(null);
> > ExecutorService	es	=	ExecutorService.getExecutorService();
> > IConfigElement	ce	=	ConfigUtil.loadConfigFromXML( "C:\\saschat\\installed\\eclipseEuropa\\workspace\\XMLProgramming\\xmls\\demo.logic.xml");
> > es.setConfigElement(ce);
> > es.executeLogic("example3");

> }

}