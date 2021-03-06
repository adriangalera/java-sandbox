package codewars;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;


public class ParseMolecule {

    public static Map<String, Integer> getAtoms(String formula) {
        Map<String, Integer> atoms = new HashMap<>();

        formula = processGroupRegex(formula, atoms, Pattern.compile("([{]([^{]*?)[}]([0-9])*)"));
        formula = processGroupRegex(formula, atoms, Pattern.compile("([\\[]([^\\[]*?)[\\]]([0-9])*)"));
        formula = processGroupRegex(formula, atoms, Pattern.compile("([(]([^(]*?)[)]([0-9])*)"));
        formula = processSimpleElementRegex(formula, atoms, Pattern.compile("([A-Z][a-z]?)([0-9]*)"));

        if (!formula.isEmpty() && !hasTrailingSeparators(formula)) {
            throw new IllegalArgumentException();
        }

        return atoms;
    }

    private static boolean hasTrailingSeparators(String formula) {
        return formula.equals("()") || formula.equals("[]") || formula.equals("{}");
    }

    private static String processSimpleElementRegex(String formula, Map<String, Integer> atoms,
                                                    Pattern nonGroupedRegex) {
        Matcher m = nonGroupedRegex.matcher(formula);

        while (m.find()) {
            String match = m.group();
            formula = formula.replace(match, "");
            String element = m.group(1);
            String count = m.group(2);
            Integer atomCount = count.isEmpty() ? 1 : Integer.parseInt(count);
            Integer currentCount = atoms.getOrDefault(element, 0);
            atoms.put(element, currentCount + atomCount);
        }
        return formula;
    }

    private static String processGroupRegex(String formula, Map<String, Integer> atoms, Pattern groupRegex) {
        Matcher groupMatcher = groupRegex.matcher(formula);

        while (groupMatcher.find()) {
            String fullGroup = groupMatcher.group(1);
            formula = formula.replace(fullGroup, "");
            String groupContent = groupMatcher.group(2);
            String groupMultiplier = groupMatcher.group(3);
            Integer atomCount = groupMultiplier == null ? 1 : Integer.parseInt(groupMultiplier);
            Map<String, Integer> groupAtoms = getAtoms(groupContent);
            for (String element : groupAtoms.keySet()) {
                groupAtoms.put(element, groupAtoms.get(element) * atomCount);
            }
            groupAtoms.forEach((key, value) -> {
                atoms.merge(key, value, Integer::sum);
            });
        }
        return formula;
    }


    @Test
    public void testWater() {
        String name = "Water";
        String formula = "H2O";
        Map<String, Integer> expected = new HashMap<>();
        expected.put("H", 2);
        expected.put("O", 1);
        System.out.println(expected);
        assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));
    }

    @Test
    public void testWater2() {
        String name = "Water2";
        String formula = "H2O2";
        Map<String, Integer> expected = new HashMap<>();
        expected.put("H", 2);
        expected.put("O", 2);
        System.out.println(expected);
        assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));
    }

    @Test
    public void testWater200() {
        String name = "Water200";
        String formula = "H2O200";
        Map<String, Integer> expected = new HashMap<>();
        expected.put("H", 2);
        expected.put("O", 200);
        System.out.println(expected);
        assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));
    }

    @Test
    public void testWater200_2() {
        String name = "Water200";
        String formula = "H200O200";
        Map<String, Integer> expected = new HashMap<>();
        expected.put("H", 200);
        expected.put("O", 200);
        System.out.println(expected);
        assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));
    }

    @Test
    public void testFremysSalt() {
        String name = "Fremy's salt";
        String formula = "K4[ON(SO3)2]2";
        Map<String, Integer> expected = new HashMap<>();
        expected.put("K", 4);
        expected.put("O", 14);
        expected.put("N", 2);
        expected.put("S", 4);
        System.out.println(expected);
        assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));
    }

    @Test
    public void testFe() {
        String name = "fe";
        String formula = "Fe(NO3)2";
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Fe", 1);
        expected.put("N", 2);
        expected.put("O", 6);
        System.out.println(expected);
        assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        ParseMolecule.getAtoms("pie");
    }

    @Test
    public void testCyclopentadienyliron() {
        String name = "cyclopentadienyliron dicarbonyl dimer";
        String formula = "(C5H5)Fe(CO)2CH3";
        Map<String, Integer> expected = new HashMap<>();
        expected.put("C", 8);
        expected.put("H", 8);
        expected.put("Fe", 1);
        expected.put("O", 2);
        assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));

    }

    @Test
    public void testEvilWater() {
        String name = "evil water";
        String formula = "{((H)2)[O]}";
        Map<String, Integer> expected = new HashMap<>();
        expected.put("H", 2);
        expected.put("O", 1);
        System.out.println(expected);
        assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongFormula() {
        ParseMolecule.getAtoms("Mg(OH");
    }
}
