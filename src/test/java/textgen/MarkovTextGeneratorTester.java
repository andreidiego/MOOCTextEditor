package textgen;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MarkovTextGeneratorTester {

	MarkovTextGenerator markovTextGeneratorLoL;
	String shortText = "This is just a short text.";
	String mediumText = "This is a text. "
			+ "It is not a short text though. "
			+ "It is rather a medium text.";
	String longText = "This is yet another text. "
			+ "Don't be misleaded by thinking it is just a short text though. "
			+ "Furthermore, it is not just a medium text either. "
			+ "It is in fact, as you might be expecting by now (after so many clues), a much longer text. "
			+ "That said, let's get back to work and finally see our Markov Text Generator trainner in action.";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		markovTextGeneratorLoL = new MarkovTextGeneratorLoL(new Random());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTrain() {
		markovTextGeneratorLoL.train(shortText);
		assertEquals("Word List does not match!",
				"This: is->\n"
				+ "is: just->\n"
				+ "just: a->\n"
				+ "a: short->\n"
				+ "short: text.->\n"
				+ "text.: This->\n",
				markovTextGeneratorLoL.toString());
	}

	@Test
	public void testTrainMedium() {
		markovTextGeneratorLoL.train(mediumText);
		assertEquals("Word List does not match!",
				"This: is->\n"
				+ "is: a->not->rather->\n"
				+ "a: text.->short->medium->\n"
				+ "text.: It->This->\n"
				+ "It: is->is->\n"
				+ "not: a->\n"
				+ "short: text->\n"
				+ "text: though.->\n"
				+ "though.: It->\n"
				+ "rather: a->\n"
				+ "medium: text.->\n",
				markovTextGeneratorLoL.toString());
	}

	@Test
	public void testTrainLonger() {
		markovTextGeneratorLoL.train(longText);
		assertEquals("Word List does not match!",
				"This: is->\n"
				+ "is: yet->just->not->in->\n"
				+ "yet: another->\n"
				+ "another: text.->\n"
				+ "text.: Don't->That->\n"
				+ "Don't: be->\n"
				+ "be: misleaded->expecting->\n"
				+ "misleaded: by->\n"
				+ "by: thinking->now->\n"
				+ "thinking: it->\n"
				+ "it: is->is->\n"
				+ "just: a->a->\n"
				+ "a: short->medium->much->\n"
				+ "short: text->\n"
				+ "text: though.->either.->\n"
				+ "though.: Furthermore,->\n"
				+ "Furthermore,: it->\n"
				+ "not: just->\n"
				+ "medium: text->\n"
				+ "either.: It->\n"
				+ "It: is->\n"
				+ "in: fact,->action.->\n"
				+ "fact,: as->\n"
				+ "as: you->\n"
				+ "you: might->\n"
				+ "might: be->\n"
				+ "expecting: by->\n"
				+ "now: (after->\n"
				+ "(after: so->\n"
				+ "so: many->\n"
				+ "many: clues),->\n"
				+ "clues),: a->\n"
				+ "much: longer->\n"
				+ "longer: text.->\n"
				+ "That: said,->\n"
				+ "said,: let's->\n"
				+ "let's: get->\n"
				+ "get: back->\n"
				+ "back: to->\n"
				+ "to: work->\n"
				+ "work: and->\n"
				+ "and: finally->\n"
				+ "finally: see->\n"
				+ "see: our->\n"
				+ "our: Markov->\n"
				+ "Markov: Text->\n"
				+ "Text: Generator->\n"
				+ "Generator: trainner->\n"
				+ "trainner: in->\n"
				+ "action.: This->\n",
				markovTextGeneratorLoL.toString());
	}

	@Test
	public void testGenerateText() {
		// fail("Not yet implemented");
	}

	@Test
	public void testRetrain() {
		// fail("Not yet implemented");
	}

}
