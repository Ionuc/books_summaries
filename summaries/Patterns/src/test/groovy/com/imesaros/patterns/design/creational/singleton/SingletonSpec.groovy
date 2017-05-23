package com.imesaros.patterns.design.creational.singleton

import spock.lang.Specification

class SingletonSpec extends Specification {

    def 'should return the same instance'() {
        given:
        Student student1
        Student student2

        when:
        student1 = singleton.getInstance();
        student2 = singleton.getInstance();

        then:
        student1 == student2

        where:
        singleton << [
                new LazySingleton(),
                new EagerSingleton()]
    }

    def 'should return the same instance in concurrent environment'() {
        given:
        def threads = []
        (1..200).forEach({ i ->
            def thread = new LocalThread(singleton)
            threads << thread
            thread.start()
        })

        expect:
        (1..199).forEach({ i ->
            threads[i].student == threads[0].student
        })

        where:
        singleton << [
                new LazySingleton(),
                new EagerSingleton()]

    }

    class LocalThread extends Thread {
        Student student
        Singleton singleton

        LocalThread(Singleton singleton){
            this.singleton = singleton
        }

        @Override
        public void run() {
            student = singleton.getInstance();
        }
    }
}
