package com.imesaros.patterns.design.structural

import com.imesaros.patterns.design.structural.proxy.CommandExecutorProxy
import spock.lang.Specification

class CommandExecutorProxySpec extends Specification {

    def 'should not allow non-admin users to execute rm '() {
        given:
        CommandExecutorProxy proxy = new CommandExecutorProxy('imesaros', 'otherPassword')

        when:
        proxy.runCommand('rm test');

        then:
        def exception = thrown(Exception)
        exception.message == 'rm command is not allowed for non-admin users.'
    }
}
