package com.sereia.renan.cli;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.Arrays;
import java.util.stream.Stream;

@QuarkusMain
public class MainQuarkus implements QuarkusApplication {

//    @Inject
//    @Channel("parametros")
//    Emitter<String> emitter;

//    ./mvnw quarkus:dev -Dquarkus.args="a,b,c" PASSANDO OS ARGS
        @Override
        public int run(String... args) throws Exception {
//            Stream.of(args).forEach(emitter::send);
            System.out.println("Quarkus main application" + Arrays.toString(args));
            Quarkus.waitForExit();
            return 0;
        }



//    public static void main(String[] args) {
//        Quarkus.run(MainQuarkus2.class, args);
//    }
//
//    public static class MainQuarkus2 implements QuarkusApplication {
//        @Override
//        public int run(String... args) throws Exception {
//            System.out.println("Quarkus main application" + Arrays.toString(args));
//            Quarkus.waitForExit();
//            return 0;
//        }
//    }
}



