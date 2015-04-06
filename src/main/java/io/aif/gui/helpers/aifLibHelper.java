package io.aif.gui.helpers;

import io.aif.gui.model.File;
import io.aif.gui.model.ModelHendler;
import io.aif.gui.model.results.*;
import io.aif.language.common.*;
import io.aif.language.semantic.ISemanticDict;
import io.aif.language.semantic.ISemanticNode;
import io.aif.language.semantic.SemanticDictBuilder;
import io.aif.language.sentence.separators.classificators.ISeparatorGroupsClassifier;
import io.aif.language.sentence.separators.extractors.ISeparatorExtractor;
import io.aif.language.sentence.separators.groupers.ISeparatorsGrouper;
import io.aif.language.sentence.splitters.AbstractSentenceSplitter;
import io.aif.language.token.ITokenSeparatorExtractor;
import io.aif.language.token.TokenSplitter;
import io.aif.language.token.comparator.ITokenComparator;
import io.aif.language.word.IWord;
import io.aif.language.word.comparator.IGroupComparator;
import io.aif.language.word.dict.DictBuilder;
import io.aif.language.word.dict.WordPlaceHolderMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class aifLibHelper {

    private String text;

    public aifLibHelper(File file) {

        try {
            text = FileHelper.readAllTextFromFile(file);
        } catch (IOException e) {
            text = null;
            e.printStackTrace();
        }
    }

    public void SSplit() {

        if(text != null) {
            final TokenSplitter tokenSplitter = new TokenSplitter();
            final ISplitter<List<String>, List<String>> sentenceSplitter = AbstractSentenceSplitter.Type.HEURISTIC.getInstance();
            final List<List<String>> result = sentenceSplitter.split(tokenSplitter.split(text));

            StringBuilder sbuf = new StringBuilder();

            result.stream().forEach( s -> {
                s.stream().forEach(k -> {
                            sbuf.append(k);
                            sbuf.append(" ");
                        }
                        );
                sbuf.append("\n");
            });

            ModelHendler.addResult(new SSplitResult(sbuf.toString()));
        }


    }

    public void Ess() {

        if (text != null) {
            final TokenSplitter tokenSplitter = new TokenSplitter();
            final ISeparatorExtractor separatorExtractor = ISeparatorExtractor.Type.PROBABILITY.getInstance();
            final ISeparatorsGrouper separatorsGrouper = ISeparatorsGrouper.Type.PROBABILITY.getInstance();
            final ISeparatorGroupsClassifier sentenceSeparatorGroupsClassificatory = ISeparatorGroupsClassifier.Type.PROBABILITY.getInstance();

            final List<String> tokens = tokenSplitter.split(text);
            final Optional<List<Character>> optSeparators = separatorExtractor.extract(tokens);
            if (!optSeparators.isPresent()) {
                System.out.println("NO_SEPARATOR_WERE_FOUND");
                return;
            }
            final List<Character> separators = optSeparators.get();

            final List<Set<Character>> separatorsGroupsUnclasify = separatorsGrouper.group(tokens, separators);
            final Map<ISeparatorGroupsClassifier.Group, Set<Character>> separatorsGroups = sentenceSeparatorGroupsClassificatory.classify(tokens, separatorsGroupsUnclasify);

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
    }

    public void DBuild() {

        if(text != null) {
            final TokenSplitter tokenSplitter = new TokenSplitter();
            final AbstractSentenceSplitter sentenceSplitter = AbstractSentenceSplitter.Type.HEURISTIC.getInstance();
            final List<String> tokens = tokenSplitter.split(text);
            final List<List<String>> sentences = sentenceSplitter.split(tokens);
            final List<String> filteredTokens = sentences.stream().flatMap(List::stream).collect(Collectors.toList());
            final ITokenComparator tokenComparator = ITokenComparator.defaultComparator();

            final DictBuilder dictBuilder = new DictBuilder();
            final IDict dict = dictBuilder.build(filteredTokens);

            final Map<String, List<String>> results = new HashMap<>();

            Set<IWord> words = dict.getWords();

            words.stream().forEach( s -> {

                List<String> allWords = new ArrayList<>();
                allWords.addAll(s.getAllTokens());
                results.put(s.getRootToken(), allWords);

            });

            ModelHendler.getResults().add(new DBuildResult(results));
        }

    }

    public void TSplit() {

        if(text != null) {
            final TokenSplitter tokenSplitter = new TokenSplitter();
            final List<String> result = tokenSplitter.split(text);

            ModelHendler.getResults().add(new TSplitResult(result));
        }
    }

    public void Est() {

        final ITokenSeparatorExtractor extractor = ITokenSeparatorExtractor.Type.PROBABILITY.getInstance();
        final Optional<List<Character>> separators = extractor.extract(text);

        if(separators.isPresent())
            ModelHendler.getResults().add(new ESTResult(separators.get()));

    }

    public void SBuild() {
        if(text != null) {
            final TokenSplitter tokenSplitter = new TokenSplitter();
            final AbstractSentenceSplitter sentenceSplitter = AbstractSentenceSplitter.Type.HEURISTIC.getInstance();
            final List<String> tokens = tokenSplitter.split(text);
            final List<List<String>> sentences = sentenceSplitter.split(tokens);
            final List<String> filteredTokens = sentences.stream().flatMap(List::stream).collect(Collectors.toList());

            final IDictBuilder dictBuilder = new DictBuilder();
            final IDict<IWord> dict = dictBuilder.build(filteredTokens);
            final IMapper<Collection<String>, List<IWord.IWordPlaceholder>> toWordPlaceHolderMapper = new WordPlaceHolderMapper((ISearchable<String, IWord>)dict);
            final List<IWord.IWordPlaceholder> placeholders = toWordPlaceHolderMapper.map(filteredTokens);

            final ISeparatorExtractor testInstance = ISeparatorExtractor.Type.PROBABILITY.getInstance();
            final ISeparatorsGrouper separatorsGrouper = ISeparatorsGrouper.Type.PROBABILITY.getInstance();
            final ISeparatorGroupsClassifier sentenceSeparatorGroupsClassificatory = ISeparatorGroupsClassifier.Type.PROBABILITY.getInstance();
            final List<Character> separators = testInstance.extract(tokens).get();
            final Map<ISeparatorGroupsClassifier.Group, Set<Character>> grouppedSeparators = sentenceSeparatorGroupsClassificatory.classify(tokens, separatorsGrouper.group(tokens, separators));


            final SemanticDictBuilder semanticDictBuilder = new SemanticDictBuilder(grouppedSeparators);
            final ISemanticDict semanticDict = semanticDictBuilder.build(placeholders);

            final List<ISemanticNode<IWord>> sortedNodes = semanticDict.getWords().stream().sorted((w2, w1) -> ((Double) w1.weight()).compareTo(w2.weight())).collect(Collectors.toList());

            ModelHendler.getResults().add(new SBuildResult(sortedNodes));

        }
    }


}
