package com.hcc;

@interface Container {
    String value();
    String type() default "singleton";
    String scope() default "application";
    String[] exclude() default {};
    String[] include() default {};
    String[] aliases() default {};
    String[] properties() default {};
    String[] initMethod() default {};
    String[] destroyMethod() default {};
    String[] lookupMethod() default {};
    String[] factoryMethod() default {};
    String[] factoryBean() default {};
    String[] factoryBeanName() default {};
    String[] factoryBeanMethod() default {};
    String[] factoryBeanArgs() default {};
    String[] factoryBeanProperties() default {};
    String[] factoryBeanPostProcessors() default {};
    String[] factoryBeanPreProcessors() default {};
    String[] factoryBeanCustomizers() default {};
    String[] factoryBeanInterceptors() default {};
    String[] factoryBeanAdvisors() default {};
    String[] factoryBeanInstantiationListeners() default {};
    String[] factoryBeanDestructionListeners() default {};
}
