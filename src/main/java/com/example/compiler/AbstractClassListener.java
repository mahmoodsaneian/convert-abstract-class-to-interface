package com.example.compiler;

import gen.com.example.compiler.JavaBaseListener;
import gen.com.example.compiler.JavaParser;

import java.util.ArrayList;
import java.util.List;

public class AbstractClassListener extends JavaBaseListener {

    private StringBuilder output = new StringBuilder();
    private boolean isAbstractClass = false;
    private boolean hasOnlyAbstractMethods = true;
    private String className;
    private List<String> abstractMethods = new ArrayList<>();

    @Override
    public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        // Check if the type declaration is a class and has the 'abstract' modifier
        if (ctx.classOrInterfaceModifier() != null) {
            for (JavaParser.ClassOrInterfaceModifierContext modifier : ctx.classOrInterfaceModifier()) {
                if (modifier.getText().equals("abstract") && ctx.classDeclaration() != null) {
                    isAbstractClass = true;
                    className = ctx.classDeclaration().Identifier().getText();
                    break;
                }
            }
        }
    }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if (isAbstractClass) {
            // Check if the method is abstract (ends with a semicolon)
            if (ctx.methodDeclarationRest().getText().endsWith(";")) {
                // Extract method signature
                String methodSignature;
                if (ctx.type() != null) {
                    // Method has a return type
                    methodSignature = ctx.type().getText() + " " + ctx.Identifier().getText() +
                            ctx.formalParameters().getText() + ";";
                } else {
                    // Method is void (no return type)
                    methodSignature = "void " + ctx.Identifier().getText() +
                            ctx.formalParameters().getText() + ";";
                }
                abstractMethods.add(methodSignature);
            } else {
                // Method is not abstract
                hasOnlyAbstractMethods = false;
            }
        }
    }

    @Override
    public void exitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        if (isAbstractClass && hasOnlyAbstractMethods) {
            // Convert abstract class to interface
            output.append("interface ").append(className).append(" {\n");

            // Add abstract methods to the interface
            for (String method : abstractMethods) {
                output.append("    ").append(method).append("\n");
            }

            output.append("}\n\n");
        }

        // Reset flags for the next type declaration
        isAbstractClass = false;
        hasOnlyAbstractMethods = true;
        abstractMethods.clear();
    }

    public String getOutput() {
        return output.toString();
    }
}
