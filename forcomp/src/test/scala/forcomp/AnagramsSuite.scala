package forcomp

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Anagrams._

@RunWith(classOf[JUnitRunner])
class AnagramsSuite extends FunSuite  {

  test("wordOccurrences: abcd") {
    assert(wordOccurrences("abcd") === List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
  }

  test("wordOccurrences: Robert") {
    assert(wordOccurrences("Robert") === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }


  test("sentenceOccurrences: abcd e") {
    assert(sentenceOccurrences(List("abcd", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
  }


  test("dictionaryByOccurrences.get: eat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) === Some(Set("ate", "eat", "tea")))
  }

  test("word anagrams: married") {
    assert(wordAnagrams("married").toSet === Set("married", "admirer"))
  }

  test("word anagrams: player") {
    assert(wordAnagrams("player").toSet === Set("parley", "pearly", "player", "replay"))
  }



  test("subtract: lard - r") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    assert(subtract(lard, r) === lad)
  }

  test("subtract: anagram - a ") {
    val anagram = List(('a', 3), ('g', 1), ('m', 1), ('n', 1), ('r', 1))
    val a = List(('a', 1))
    var anagrm = List(('a', 2), ('g', 1), ('m', 1), ('n', 1), ('r', 1))
    assert(subtract(anagram, a) === anagrm)
  }

  test("subtract: anagram - aa ") {
    val anagram = List(('a', 3), ('g', 1), ('m', 1), ('n', 1), ('r', 1))
    val a2 = List(('a', 2))
    var angrm = List(('a', 1), ('g', 1), ('m', 1), ('n', 1), ('r', 1))
    assert(subtract(anagram, a2) === angrm)
  }

  test("subtract: anagram - aaa ") {
    val anagram = List(('a', 3), ('g', 1), ('m', 1), ('n', 1), ('r', 1))
    val a3 = List(('a', 3))
    var ngrm = List(('g', 1), ('m', 1), ('n', 1), ('r', 1))
    assert(subtract(anagram, a3) === ngrm)
  }


  test("combinations: []") {
    assert(combinations(Nil) === List(Nil))
  }

  test("combinations: abba") {
    val abba = List(('a', 2), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2))
    )
    assert(combinations(abba).toSet === abbacomb.toSet)
  }

  test("combinations: yuzu") {
    val yuzu = List(('u', 2), ('y', 1), ('z', 1))
    val yuzuComb = List(
      List(),
      List(('u', 1)),
      List(('u', 1), ('y', 1)),
      List(('u', 1), ('y', 1), ('z', 1)),
      List(('u', 1), ('z', 1)),
      List(('u', 2)),
      List(('u', 2), ('y', 1)),
      List(('u', 2), ('y', 1), ('z', 1)),
      List(('u', 2), ('z', 1)),
      List(('y', 1)),
      List(('y', 1), ('z', 1)),
      List(('z', 1))
    )
    assert(combinations(yuzu).toSet === yuzuComb.toSet)
  }


  test("sentence anagrams: []") {
    val sentence = List()
    assert(sentenceAnagrams(sentence) === List(Nil))
  }

  test("sentence anagrams: Linux rulez") {
    val sentence = List("Linux", "rulez")
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

}
