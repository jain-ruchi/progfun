package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
        if (c == 0 || c == r) {
            1
        } else {
            pascal(c-1, r-1) + pascal(c, r-1)
        }
    }

  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
        def balanceAux(openParens: List[Char], chars: List[Char]): Boolean = {
            if (chars.isEmpty) {
                openParens.isEmpty
            } else if (chars.head == '(') {
                balanceAux('(' :: openParens, chars.tail)
            } else if (chars.head == ')') {
                if (openParens.isEmpty || openParens.head != '(') {
                    false
                } else {
                    balanceAux(openParens.tail, chars.tail)
                }
            } else {
                balanceAux(openParens, chars.tail)
            }
        }

        balanceAux(Nil, chars)
    }

  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
        if (money == 0 || coins.isEmpty) {
            0
        } else if (money.toFloat / coins.head == 1) {
            1 + countChange(money, coins.tail)
        } else if (money.toFloat / coins.head < 1) {
            countChange(money, coins.tail)
        } else {
            countChange(money - coins.head, coins) + countChange(money, coins.tail)
        }
    }

  }
