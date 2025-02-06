package com.example.compiler;

import gen.com.example.compiler.JavaLexer;
import gen.com.example.compiler.JavaParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompilerApplication {

	public static void main(String[] args) {
		String inputJavaCode = "abstract class MyClass {\n" +
				"    abstract void myMethod();\n" +
				"    abstract int anotherMethod(int param);\n" +
				"}\n";

		JavaLexer lexer = new JavaLexer(CharStreams.fromString(inputJavaCode));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JavaParser parser = new JavaParser(tokens);
		ParseTree tree = parser.compilationUnit();

		// Create and apply the custom listener
		AbstractClassListener converter = new AbstractClassListener();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(converter, tree);

		System.out.println(converter.getOutput());
	}

}
