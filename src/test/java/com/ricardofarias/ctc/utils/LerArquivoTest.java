package com.ricardofarias.ctc.utils;

import com.ricardofarias.ctc.exceptions.ArquivoNaoEncontradoException;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LerArquivoTest {

    private final String PATH_BASE_DE_DADOS = "arquivoOrigem.txt";
    private final String PATH_RESULTADO = "resultado-frases.txt";

    @BeforeAll
    void setUp() throws ArquivoNaoEncontradoException {
        LerArquivo.lerArquivoBaseDeDados(PATH_BASE_DE_DADOS);
    }

    @Test
    @DisplayName("Deve gerar o arquivo resultado-frases.txt com as 50 mil frases.")
    void deveGerarArquivoResultado_quandoChamadoLerArquivoBaseDados() {

        File file = new File(PATH_BASE_DE_DADOS);

        Assertions.assertTrue(file.exists());
    }

    @Test
    @DisplayName("Deve gerar a exception de Arquivo não encontrado.")
    void deveGerarExceptionArquivoNaoEncontrado_quandoChamadoLerArquivoBaseDados() {

        Assertions.assertThrows(ArquivoNaoEncontradoException.class,
                () -> LerArquivo.lerArquivoBaseDeDados("arquivo-nao-existe.txt"));
    }

    @Test
    @DisplayName("Deve verificar de as frases estão ordendas pela quantidade encontrada.")
    void deveGerarAsFrasesOrdenadasPorTamanho_quandoChamadoLerArquivoBaseDados() throws ArquivoNaoEncontradoException {

        boolean isOrdenado = true;

        List<Integer> listaContagemFrases;

        listaContagemFrases = retornaMapFrases();

        for (int i=0; i < listaContagemFrases.size() - 1; i++) {
            if(listaContagemFrases.get(i) < listaContagemFrases.get(i + 1))
                isOrdenado = false;
        }

        Assertions.assertTrue(isOrdenado);
    }

    @Test
    @DisplayName("Deve verificar se o aqruivo resultado possui no máximo 50 mil frases.")
    void deveVerificarQunatidadeMaximaDeFrasesResultado_quandoChamadoLerArquivoBaseDados() throws ArquivoNaoEncontradoException {

        boolean isOrdenado = true;

        List<Integer> listaContagemFrases;

        listaContagemFrases = retornaMapFrases();

        Assertions.assertTrue(listaContagemFrases.size() <= 50001);
    }

    private List<Integer> retornaMapFrases() throws ArquivoNaoEncontradoException {
        String linha;
        String[] frasesPorLinha;
        List<Integer> quantidadeFrase = new ArrayList<>();

        File file = new File(PATH_RESULTADO);

        if(!file.exists())
            throw new ArquivoNaoEncontradoException(PATH_RESULTADO);

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader (fr, 1024)) {

            while ((linha = br.readLine()) != null) {
                frasesPorLinha = linha.split(" \\| ");
                quantidadeFrase.add(Integer.parseInt(frasesPorLinha[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return quantidadeFrase;
    }
}