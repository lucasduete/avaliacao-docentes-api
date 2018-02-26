package io.github.avaliacaodocentes.factory;

public class Fabrica {

    public static FabricaDaoInterface criarFabricaDaoPostgres() {
        return new FabricaDaoPostgres();
    }
}
