package com.kriskrause.learning;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Data {
   public static String[] Letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
      "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
      "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" 
   };

   public static String[] First100 = { "the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he", "was", "for", "on", "are", "as",
		"with", "his", "they", "I", "at", "be", "this", "have", "from",
		"or", "one", "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", "your", "can", "said", "there", "use", 
		"an", "each", "which", "she", "do", "how", "their", "if", "will","up", "other", "about", "out", "many", "then", "them", "these", 
		"so", "some", "her", "would", "make", "like", "him", "into", "time", "has", "look", "two", "more", "write", "go", "see",
		"number", "no", "way", "could", "people", "my", "than", "first", "water", "been", "call", "who", "oil", "sit", "now", "find", 
		"long", "down", "day", "did", "get", "come", "made", "may", "part"
   };

   public static String[] Second100 = { "over", "new", "sound", "take", "only", "little", "work", "know", "place", "year", "live", "me", "back", 
      "give", "most", "very", "after", "thing", "our", "just", "name", "good", "sentence", "man", "think", "say", "great", "where", "help", 
   	"through", "much", "before", "line", "right", "too", "mean", "old", "any", "same", "tell", "boy", "follow", "came", "want", "show", 
   	"also", "around", "form", "three", "small", "set", "put", "end", "does", "another", "well", "large", "must", "big", "even", "such", 
   	"because", "turn", "here", "why", "ask", "went", "men", "read", "need", "land", "different", "home", "us", "move", "try", "kind", "hand", 
   	"picture", "again", "change", "off", "play", "spell", "air", "away", "animal", "house", "point", "page", "letter", "mother", "answer", 
   	"found", "study", "still", "learn", "should", "America", "world"
   };

   public static String[] Third100 = { "high", "every", "near", "add", "food", "between", "own", "below", "country", "plant", "last", "school", "father", 
		"keep", "tree", "never", "start", "city", "earth", "eye", "light", "thought", "head", "under", "story", "saw", "left", "don’t", "few", "while", "along", 
		"might", "close", "something", "seem", "nest", "hard", "open", "example", "begin", "life", "always", "those", "both", "paper", "together", "got", "group", 
		"often", "run", "important", "until", "children", "side", "feet", "car", "mile", "night", "walk", "white", "sea", "began", "grow", "took", "river", "four", 
		"carry", "state", "once", "book", "hear", "stop", "without", "second", "later", "miss", "idea", "enough", "eat", "face", "watch", "far", "Indian", "real", 
		"almost", "let", "above", "girl", "sometimes", "mountain", "cut", "young", "talk", "soon", "list", "song", "begin", "leave", "family", "it's"
   };

   public static String[] Fourth100 = {
   	"body", "order", "listen", "farm", "music", "red", "wind", "pulled", "color", "door", "rock", "draw", "stand", "sure", "space", "voice", "sun", "become", "covered", 
   	"seen", "questions", "top", "fast", "cold", "fish", "ship", "several", "cried", "area", "across", "hold", "plan", "mark", "today", "himself", "notice", "dog", 
   	"during", "toward", "south", "horse", "short", "five", "sing", "birds", "better", "step", "war", "problem", "best", "morning", "ground", "complete", "however", 
   	"passed", "fall", "room", "low", "vowel", "king", "knew", "hours", "true", "town", "since", "black", "hundred", "I'll", "ever", "products", "against", "unit", 
   	"piece", "happened", "pattern", "figure", "told", "whole", "numeral", "certain", "usually", "measure", "table", "field", "didn't", "remember", "north", "travel",
   	"friends", "early", "slowly", "wood", "easy", "waves", "money", "fire", "heard", "reached", "map", "upon"
   };

   public static String[] Fifth100 = {
   	"done", "decided", "plane", "filled", "English", "contain", " system", "heat", "road", "course", "behind", "full", "half", "surface", "ran", "hot", "ten", 
   	"produce", "round", "check", "fly", "building", "boat", "object", "gave", "ocean", "game", "am", "box", "class", "force", "rule", "finally", "note", 
   	"brought", "among", "wait", "nothing", "understand", "noun", "correct", "rest", "warm", "power", "oh", "carefully", "common", "cannot", "quickly", 
   	"scientists", "bring", "able", "person", "inside", "explain", "six", "became", "wheels", "dry", "size", "shown", "stay", "though", "dark", "minutes",
   	"green", "language", "ball", "strong", "known", "shape", "material", "verb", "island", "deep", "special", "stars", "week", "thousands", "heavy", "front",
   	"less", "yes", "fine", "feel", "machine", "clear", "pair", "fact", "base", "equation", "circle", "inches", "ago", "yet", "include", "street", "stood",
   	"government", "built"
   };

   public static String[] Sixth100 = {
   	"can't", "picked", "legs", "beside", "matter", "simple", "sat", "gone", "square", "cells", "main", "sky", "syllables", "paint", "winter", "grass", "perhaps", 
   	"mind", "wide", "million", "bill", "love", "written", "west", "felt", "cause", "length", "lay", "suddenly", "rain", "reason", "weather", "test", "exercise",
   	"kept", "root", "direction", "eggs", "interest", "instruments", "center", "train", "arms", "meet", "farmers", "blue", "brother", "third", "ready", "wish", 
   	"race", "months", "anything", "drop", "present", "paragraph", "divided", "developed", "beautiful", "raised", "general", "window", "store", "represent", 
   	"energy", "difference", "job", "soft", "subject", "distance", "edge", "whether", "Europe", "heart", "past", "clothes", "moon", "site", "sign", "flowers", 
   	"region", "sum", "record", "shall", "return", "summer", "finished", "teacher", "believe", "wall", "discovered", "held", "dance", "forest", "wild", "describe", 
   	"members", "probably", "happy", "drive"
   };

   public static String[] Seventh100 = {
   	"cross", "already", "hair", "rolled", "speak", "instead", "age", "bear", "solve", "phrase", "amount", "wonder", "appear", "soil", "scale", "smiled", "metal", 
   	"bed", "pounds", "angle", "son", "copy", "although", "fraction", "either", "free", "per", "Africa", "ice", "hope", "broken", "killed", "sleep", "spring", "moment", 
   	"melody", "village", "case", "tiny", "bottom", "factors", "laughed", "possible", "trip", "result", "nation", "gold", "hole", "jumped", "quite", "milk", "poor", 
   	"snow", "type", "quiet", "let's", "ride", "themselves", "natural", "fight", "care", "temperature", "lot", "surprise", "floor", "bright", "stone", "French", "hill", 
   	"lead", "act", "died", "pushed", "everyone", "build", "beat", "baby", "method", "middle", "exactly", "buy", "section", "speed", "remain", "century", "lake", "count", 
   	"dress", "outside", "iron", "consonant", "cat", "everything", "within", "someone", "couldn't", "tall", "dictionary", "sail", "fingers"
   };

   public static String[] Eigth100 = {
   	"row", "president", "yourself", "caught", "least", "brown", "control", "fell", "catch", "trouble", "practice", "team", "climbed", "cool", "report", "God", "wrote", "cloud", 
   	"straight", "captain", "shouted", "lost", "rise", "direct", "continued", "sent", "statement", "ring", "itself", "symbols", "stick", "serve", "else", "wear", "party", "child", 
   	"plains", "bad", "seeds", "desert", "gas", "save", "suppose", "increase", "England", "experiment", "woman", "history", "burning", "engine", "coast", "cost", "design", "alone", 
   	"bank", "maybe", "joined", "drawing", "period", "business", "foot", "east", "wire", "separate", "law", "choose", "pay", "break", "ears", "single", "clean", "uncle", "glass", 
   	"touch", "visit", "hunting", "you're", "information", "bit", "flow", "grew", "express", "whose", "lady", "skin", "mouth", "received", "students", "valley", "yard", "garden", 
   	"human", "cents", "equal", "please", "art", "key", "decimal", "strange", "feeling"
   };

   public static String[] Ninth100 = {
   	"supply", "guess", "thick", "major", "corner", "silent", "blood", "observe", "electric", "trade", "lie", "tube", "insects", "rather", "spot", "necessary", "crops", "compare", 
   	"bell", "weight", "tone", "crowd", "fun", "meat", "hit", "poem", "loud", "lifted", "sand", "enjoy", "consider", "process", "doctor", "elements", "suggested", "army", "provide", 
   	"indicate", "thin", "hat", "thus", "except", "position", "property", "won't", "expect", "entered", "particular", "cook", "flat", "fruit", "swim", "bones", "seven", "tied", "terms", 
   	"mall", "interesting", "rich", "current", "board", "sense", "dollars", "park", "modern", "string", "send", "sell", "compound", "blow", "sight", "shoulder", "mine", "famous", "chief", 
   	"industry", "wasn't", "value", "Japanese", "wash", "fit", "wings", "stream", "block", "addition", "movement", "planets", "spread", "belong", "pole", "rhythm", "cattle", "safe", 
   	"exciting", "eight", "wife", "soldiers", "branches", "science", "sharp"
   };

   public static String[] Tenth100 = {
   	"company", "sister", "gun", "total", "radio", "oxygen", "similar", "deal", "we'll", "plural", "death", "determine", "action", "various", "score", "evening", "capital", "agreed", "forward", 
   	"hoe", "factories", "opposite", "stretched", "rope", "settled", "wrong", "experience", "cotton", "yellow", "chart", "rose", "apple", "isn't", "prepared", "allow", "details", "southern", 
   	"pretty", "fear", "entire", "truck", "solution", "workers", "corn", "fair", "fresh", "Washington", "substances", "printed", "shop", "Greek", "smell", "wouldn't", "suffix", "women", 
   	"tools", "ahead", "especially", "bought", "conditions", "chance", "shoes", "led", "cows", "born", "actually", "march", "track", "level", "nose", "northern", "arrived", "triangle", "afraid",
   	"create", "located", "molecules", "dead", "British", "sir", "France", "sugar", "difficult", "seat", "repeated", "adjective", "match", "division", "column", "fig", "win", "effect", "western", 
   	"office", "doesn't", "underline", "church", "huge", "steel", "view"
   };

    public static String[] Numbers = {
       "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
    };

    private static ArrayList<DataItem> _numbers = null;

    public static ArrayList<DataItem> Numbers() {
       if (_numbers != null) return _numbers;

        _numbers = new ArrayList<DataItem>();

        _numbers.add(new DataItem("1", "one"));
        _numbers.add(new DataItem("2", "two"));
        _numbers.add(new DataItem("3", "three"));
        _numbers.add(new DataItem("4", "four"));
        _numbers.add(new DataItem("5", "five"));
        _numbers.add(new DataItem("6", "six"));
        _numbers.add(new DataItem("7", "seven"));
        _numbers.add(new DataItem("8", "eight"));
        _numbers.add(new DataItem("9", "nine"));
        _numbers.add(new DataItem("10", "ten"));
        _numbers.add(new DataItem("11", "eleven"));
        _numbers.add(new DataItem("12", "twelve"));
        _numbers.add(new DataItem("13", "thirteen"));
        _numbers.add(new DataItem("14", "fourteen"));
        _numbers.add(new DataItem("15", "fifteen"));
        _numbers.add(new DataItem("16", "sixteen"));
        _numbers.add(new DataItem("17", "seventeen"));
        _numbers.add(new DataItem("18", "eighteen"));
        _numbers.add(new DataItem("19", "nineteen"));
        _numbers.add(new DataItem("20", "twenty"));
        _numbers.add(new DataItem("21", "twenty-one"));
        _numbers.add(new DataItem("22", "twenty-two"));
        _numbers.add(new DataItem("23", "twenty-three"));
        _numbers.add(new DataItem("24", "twenty-four"));
        _numbers.add(new DataItem("25", "twenty-five"));
        _numbers.add(new DataItem("26", "twenty-six"));
        _numbers.add(new DataItem("27", "twenty-seven"));
        _numbers.add(new DataItem("28", "twenty-eight"));
        _numbers.add(new DataItem("29", "twenty-nine"));
        _numbers.add(new DataItem("30", "thirty"));
        _numbers.add(new DataItem("31", "thiry-one"));

       return _numbers;
    }

    public static HashMap<Integer, ArrayList<DataItem>> DataValues = new HashMap<Integer, ArrayList<DataItem>>()
    {{
        put(0, Letters());
        put(11, Numbers());
    }};

    private static ArrayList<DataItem> _letters = null;

    public static ArrayList<DataItem> Letters() {
        if (_letters != null) return _letters;

        _letters = new ArrayList<DataItem>();

        _letters.add(new DataItem("A", "Apple"));
        _letters.add(new DataItem("B", "Ball"));
        _letters.add(new DataItem("C", "Cat"));
        _letters.add(new DataItem("D", "Dog"));
        _letters.add(new DataItem("E", "Elephant"));
        _letters.add(new DataItem("F", "Fish"));
        _letters.add(new DataItem("G", "Girl"));
        _letters.add(new DataItem("H", "Happy"));
        _letters.add(new DataItem("I", "Igloo"));
        _letters.add(new DataItem("J", "Jump"));
        _letters.add(new DataItem("K", "Kite"));
        _letters.add(new DataItem("L", "Love"));
        _letters.add(new DataItem("M", "Mom"));
        _letters.add(new DataItem("N", "No"));
        _letters.add(new DataItem("O", "Octopus"));
        _letters.add(new DataItem("P", "Please"));
        _letters.add(new DataItem("Q", "Quilt"));
        _letters.add(new DataItem("R", "Rainbow"));
        _letters.add(new DataItem("S", "Sun"));
        _letters.add(new DataItem("T", "Turtle"));
        _letters.add(new DataItem("U", "Umbrella"));
        _letters.add(new DataItem("V", "Vase"));
        _letters.add(new DataItem("W", "Worm"));
        _letters.add(new DataItem("X", "X-ray"));
        _letters.add(new DataItem("Y", "Yarn"));
        _letters.add(new DataItem("Z", "Zebra"));

        _letters.add(new DataItem("a", "apple"));
        _letters.add(new DataItem("b", "ball"));
        _letters.add(new DataItem("c", "cat"));
        _letters.add(new DataItem("d", "dog"));
        _letters.add(new DataItem("e", "elephant"));
        _letters.add(new DataItem("f", "fish"));
        _letters.add(new DataItem("g", "girl"));
        _letters.add(new DataItem("h", "happy"));
        _letters.add(new DataItem("i", "igloo"));
        _letters.add(new DataItem("j", "jump"));
        _letters.add(new DataItem("k", "kite"));
        _letters.add(new DataItem("l", "love"));
        _letters.add(new DataItem("m", "mom"));
        _letters.add(new DataItem("n", "no"));
        _letters.add(new DataItem("o", "octopus"));
        _letters.add(new DataItem("p", "please"));
        _letters.add(new DataItem("q", "quilt"));
        _letters.add(new DataItem("r", "rainbow"));
        _letters.add(new DataItem("s", "sun"));
        _letters.add(new DataItem("t", "turtle"));
        _letters.add(new DataItem("u", "umbrella"));
        _letters.add(new DataItem("v", "vase"));
        _letters.add(new DataItem("w", "worm"));
        _letters.add(new DataItem("x", "x-ray"));
        _letters.add(new DataItem("y", "yarn"));
        _letters.add(new DataItem("z", "zebra"));

        return _letters;
    }

    public static String[][] DataArray = {
      Letters,
      First100,
      Second100,
      Third100,
      Fourth100,
      Fifth100,
      Sixth100,
      Seventh100,
      Eigth100,
      Ninth100,
      Tenth100,
      Numbers
   };

   public static DataItem[] convertDataItems(String[] chars) {
      ArrayList<DataItem> items = new ArrayList<DataItem>();

      for (int i = 0; i < chars.length; i++) {
         items.add(new DataItem(chars[i], chars[i]));
      }

      return items.toArray(new DataItem[items.size()]);
   }
}