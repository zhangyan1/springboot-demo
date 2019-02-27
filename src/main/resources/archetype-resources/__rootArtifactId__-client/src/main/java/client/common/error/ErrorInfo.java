#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.error;

import lombok.Data;

/**
 * Created by ${userName} on ${today}.
 */
@Data
public class ErrorInfo {
	
	
    private long code;
    private String name;
    private String msg;

    public ErrorInfo() {
        this(200, "EMPTY", "EMPTY");
    }

    public ErrorInfo(long code, String name, String msg) {
        this.code = code;
        this.name = (name == null || name.trim().equals("")) ? "EMPTY" : name;
        this.msg = (msg == null || msg.trim().equals("")) ? "EMPTY" : msg;
    }

    public String toJson(){
        return String.format("{\"code\":%s,\"name\":\"%s\",\"msg\":\"%s\"}", code, name, msg);
    }
}
