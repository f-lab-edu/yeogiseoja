package com.flab.yeogiseoja.domain.rule;

public abstract class RuleValidatorTemplate {
    private final String target;

    RuleValidatorTemplate(String target) {
        this.target = target;
    }

    public boolean executeRuleValidator() {
        return isSatisfiedBy(this.target);
    }

    abstract protected boolean isSatisfiedBy(String target);
}
