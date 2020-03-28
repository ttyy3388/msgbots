package org.beuwi.simulator.utils;

public class JsonEnterConvert
{
	public static String convert(String jsonString)
	{
		StringBuilder builder = new StringBuilder();
	    int len = jsonString.length(), i = 0;
	    String tab = "";
	    char data;
	    boolean beginEnd = true;

	    for(i = 0 ; i < len ; i ++)
	    {
	    	data = jsonString.charAt(i);

	        switch (data)
	        {
	            case '{': case '[':
	            {
	                builder.append(data);

	                if (beginEnd)
	                {
	                    tab += "\t";
	                    builder.append("\n");
	                    builder.append(tab);
	                }

	                break;
	            }
	            case '}': case ']':
	            {
	                if (beginEnd)
	                {
	                    tab = tab.substring(0, tab.length()-1);
	                    builder.append("\n");
	                    builder.append( tab );
	                }

	                builder.append(data);
	                break;
	            }
	            case '"':
	            {
	                if (jsonString.charAt(i - 1) != '\\')
	                {
	                    beginEnd = !beginEnd;
	                }

	                builder.append(data);
	                break;
	            }
	            case ',':
	            {
	            	builder.append(data);

	                if (beginEnd)
	                {
	                	builder.append("\n");
	                	builder.append( tab );
	                }

	                break;
	            }
	            default :
	            {
	            	builder.append(data);
	            }
	        }
	    }

	    return builder.toString();
	}
}
