
package com.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Example of a Spring bean.
 */
@SuppressWarnings("serial")
public abstract class AbstractBean implements BeanFactoryAware, InitializingBean, DisposableBean, Serializable {

    protected transient Logger log = Logger.getLogger(this.getClass());

    private BeanFactory beanFactory;

    protected AbstractBean() {
        this.log.info(this.getClass().getSimpleName() + " constructor invoked");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.log.info(this.getClass().getSimpleName() + ".setBeanFactory() invoked");
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() {
        this.log.info(this.getClass().getSimpleName() + ".afterPropertiesSet() invoked");
    }

    @Override
    public void destroy() {
        this.log.info(this.getClass().getSimpleName() + ".destroy() invoked");
    }

    public String info() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode())
          + "\n  beanFactory="
          + (this.beanFactory != null ? this.beanFactory.toString().replaceAll(",", ",\n    ") : null);
    }

    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        this.log = Logger.getLogger(this.getClass());
    }

}

