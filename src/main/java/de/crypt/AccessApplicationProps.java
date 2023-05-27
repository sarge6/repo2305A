package de.crypt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Data
@Service
public class AccessApplicationProps {
    @Autowired
    private Environment environment;
    public AccessApplicationProps() {}
    public String getApplicationPropByName(String prop){
        return this.environment.getProperty(prop);
    }
}
