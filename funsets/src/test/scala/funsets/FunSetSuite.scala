package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val negativeSet: Set = (x: Int) => x < 0
    val positiveSet: Set = (x: Int) => x > 0
    val zeroSet: Set = (x: Int) => x == 0
    val oddSet: Set = (x: Int) => x % 2 != 0
    val evenSet: Set = (x: Int) => x % 2 == 0
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "contains(singletonSet(1), 1)")
      assert(!contains(s1, 2), "!contains(singletonSet(1), 2)")
    }
  }

  test("union") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "contains(union(s1, s2), 1)")
      assert(contains(s, 2), "contains(union(s1, s2), 2)")
      assert(!contains(s, 3), "!contains(union(s1, s2), 3)")
    }
  }

  test("intersect") {
    new TestSets {
      val positiveOddSet = intersect(positiveSet, oddSet)
      assert(contains(positiveOddSet, 21), "contains(positiveOddSet, 21)")
      assert(!contains(positiveOddSet, 20), "!contains(positiveOddSet, 20)")
      assert(!contains(positiveOddSet, -13), "!contains(positiveOddSet, -13)")
    }
  }

  test("diff") {
    new TestSets {
      val nonZeroEvenSet = diff(evenSet, zeroSet)
      assert(contains(nonZeroEvenSet, 2), "contains(nonZeroEvenSet, 2)")
      assert(!contains(nonZeroEvenSet, 0), "!contains(nonZeroEvenSet, 0)")

      val negativeEvenSet = diff(negativeSet, oddSet)
      assert(contains(negativeEvenSet, -8), "contains(negativeEvenSet, -8)")
      assert(!contains(negativeEvenSet, -1), "!contains(negativeEvenSet, -1)")
    }
  }

  test("filter") {
    new TestSets {
      val fizzBuzz = filter(positiveSet, (x: Int) => x % 3 == 0 || x % 5 == 0)
      assert(contains(fizzBuzz, 6), "contains(fizzBuzz, 6)")
      assert(contains(fizzBuzz, 10), "contains(fizzBuzz, 10)")
      assert(contains(fizzBuzz, 15), "contains(fizzBuzz, 15)")
      assert(!contains(fizzBuzz, 16), "!contains(fizzBuzz, 16)")
    }
  }

  test("forall and exists") {
    new TestSets {
      val positiveHundreds: Set = (x: Int) => x > 0 && x % 100 == 0
      assert(forall(positiveHundreds, (x: Int) => x > 0), "forall(positiveHundreds, x > 0)")
      assert(forall(positiveHundreds, (x: Int) => x % 100 == 0), "forall(positiveHundreds, x % 100 == 0)")
      assert(forall(positiveHundreds, (x: Int) => x > 0 && x % 100 == 0), "forall(positiveHundreds, x > 0 && x % 100 == 0")
      assert(!forall(positiveHundreds, (x: Int) => x < 500), "forall(positiveHundreds, x < 500)")

      assert(exists(positiveHundreds, (x: Int) => x > 500), "exists(positiveHundreds, x > 500)")
      assert(!exists(positiveHundreds, (x: Int) => x % 101 == 0), "!exists(positiveHundreds, x % 101 == 0)")
    }
  }

  test("map") {
    new TestSets {
      val oneTwoThree: Set = (x: Int) => x == 1 || x == 3 || x == 5
      val doubled = map(oneTwoThree, (x: Int) => x * 2)

      assert(contains(doubled, 2), "contains(doubled, 2)")
      assert(contains(doubled, 6), "contains(doubled, 6)")
      assert(contains(doubled, 10), "contains(doubled, 10)")
      assert(!contains(doubled, 1), "!contains(doubled, 1)")
      assert(!contains(doubled, 3), "!contains(doubled, 3)")
      assert(!contains(doubled, 5), "!contains(doubled, 5)")
    }
  }

}
