package io.aif.gui.helpers;

import io.aif.gui.model.ModelHendler;
import io.aif.gui.model.results.*;
import io.aif.language.fact.IFact;
import io.aif.language.sentence.separators.classificators.ISeparatorGroupsClassifier;
import io.aif.language.word.IWord;
import io.aif.pipeline.factory.plain.FileTextFactory;
import io.aif.pipeline.factory.plain.ITextFactory;
import io.aif.pipeline.factory.semantic.ISemanticTextFactory;
import io.aif.pipeline.model.ISemanticText;
import io.aif.pipeline.model.IText;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class aifLibHelper {

    final private ITextFactory textFactory;
    final IText text;
    final ISemanticText semnticText;

    public aifLibHelper(Path file) {
        textFactory = new FileTextFactory(file);
        text = textFactory.build();
        semnticText = ISemanticTextFactory.build(text);
    }

    public void SSplit() {

            final StringBuilder sbuf = new StringBuilder();
            text.sentences().stream().forEach( s -> {
                s.stream().forEach(
                        k -> {
                            sbuf.append(k.getToken());
                            sbuf.append(" ");
                        }
                );
                sbuf.append("\n");
            });

            ModelHendler.addResult(new SSplitResult(sbuf.toString()));

    }

    public void Ess() {

        final Map<ISeparatorGroupsClassifier.Group, Set<Character>> separatorsGroups = text.separators();

        final Map<String, String> result = new HashMap<>();

        separatorsGroups.keySet().stream().forEach(s -> {

            final StringBuilder sbuf = new StringBuilder();
            separatorsGroups.get(s).stream().forEach(k -> {
                sbuf.append(k.toString());
                sbuf.append("\n");
            });

            result.put(s.toString(), sbuf.toString());
        });

        ModelHendler.addResult(new ESSResult(result));

    }

    public void DBuild() {

        final Map<String, List<String>> results = new HashMap<>();
        final Set<IWord> words = text.words();

        words.stream().forEach( s -> {

            List<String> allWords = new ArrayList<>();
            allWords.addAll(s.getAllTokens());
            results.put(s.getRootToken(), allWords);

        });

        ModelHendler.getResults().add(new DBuildResult(results));

    }

    public void TSplit() {

            ModelHendler.getResults().add(new TSplitResult(text.tokens()));
    }

    public void Facts() {
        Optional<Set<IFact>> facts =  semnticText.factQuery().allFacts();
        if(facts.isPresent()) {
            String result = facts.get().stream().map(IFact::toString).collect(Collectors.joining("\n"));
            ModelHendler.getResults().add(new FactResult(result));
        }
    }


    public void SBuild() {
//        if(text != null) {
//            final TokenSplitter tokenSplitter = new TokenSplitter();
//            final AbstractSentenceSplitter sentenceSplitter = AbstractSentenceSplitter.Type.HEURISTIC.getInstance();
//            final List<String> tokens = tokenSplitter.split(text);
//            final List<List<String>> sentences = sentenceSplitter.split(tokens);
//            final List<String> filteredTokens = sentences.stream().flatMap(List::stream).collect(Collectors.toList());
//
//            final IDictBuilder dictBuilder = new DictBuilder();
//            final IDict<IWord> dict = dictBuilder.build(filteredTokens);
//            final IMapper<Collection<String>, List<IWord.IWordPlaceholder>> toWordPlaceHolderMapper = new WordPlaceHolderMapper((ISearchable<String, IWord>)dict);
//            final List<IWord.IWordPlaceholder> placeholders = toWordPlaceHolderMapper.map(filteredTokens);
//
//            final ISeparatorExtractor testInstance = ISeparatorExtractor.Type.PROBABILITY.getInstance();
//            final ISeparatorsGrouper separatorsGrouper = ISeparatorsGrouper.Type.PROBABILITY.getInstance();
//            final ISeparatorGroupsClassifier sentenceSeparatorGroupsClassificatory = ISeparatorGroupsClassifier.Type.PROBABILITY.getInstance();
//            final List<Character> separators = testInstance.extract(tokens).get();
//            final Map<ISeparatorGroupsClassifier.Group, Set<Character>> grouppedSeparators = sentenceSeparatorGroupsClassificatory.classify(tokens, separatorsGrouper.group(tokens, separators));
//
//
//            final SemanticDictBuilder semanticDictBuilder = new SemanticDictBuilder(grouppedSeparators);
//            final ISemanticDict semanticDict = semanticDictBuilder.build(placeholders);
//
//            final List<ISemanticNode<IWord>> sortedNodes = semanticDict.getWords().stream().sorted((w2, w1) -> ((Double) w1.weight()).compareTo(w2.weight())).collect(Collectors.toList());
//
//            ModelHendler.getResults().add(new SBuildResult(sortedNodes));
//
//        }
    }

}
