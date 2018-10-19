package functors.bifunctor

import scala.language.higherKinds

/**
  *
  *  Similar to a functor, the BiFunctor must satisfy the functor laws:
  *   1. bimap f id id  = id
  *      first f id = id
  *      second f id = id
  *
  *      Applying a function over the identity, should return the identity.
  *
  *   2. bimap f g = first f . second g
  *
  *     Asociativity: applying bimap f g should be equal to applying f to first and g to second.
  *
  *   BiFunctors include: Either, N-Tuples
  */
trait BiFunctor[F[_, _]] {
  def biMap[A, B, C, D](input: F[A, B])(f: A => C)(g: B => D): F[C, D]

  def first[A, C](input: F[A, _])(f: A => C): F[C, _]

  def second[B, D](input: F[_, B])(f: B => D): F[_, D]
}

object BiFunctor extends App {
  implicit def eitherBiFunctor: BiFunctor[Either] = new BiFunctor[Either] {
    override def biMap[A, B, C, D](input: Either[A, B])(f: A => C)(g: B => D): Either[C, D] = {
      input.left.map(f).right.map(g)
    }

    override def first[A, C](input: Either[A, _])(f: A => C): Either[C, _] =
      input.left.map(f)

    override def second[B, D](input: Either[_, B])(g: B => D): Either[_, D] =
      input.right.map(g)
  }

  implicit def tupleBiFunctor: BiFunctor[Tuple2] = new BiFunctor[Tuple2] {
    override def biMap[A, B, C, D](input: (A, B))(f: A => C)(g: B => D): (C, D) =
      (f(input._1), g(input._2))

    override def first[A, C](input: Tuple2[A, _])(f: A => C): Tuple2[C, _] = (f(input._1), input._2)

    override def second[B, D](input: Tuple2[_, B])(f: B => D): Tuple2[_, D] = (input._1, f(input._2))
  }

  implicit class BiFunctorSyntax[A, B](a: (A, B)) {
    def biMap[C, D](f: A => C)(g: B => D)(implicit functor: BiFunctor[Tuple2]) = functor.biMap(a)(f)(g)
    def first[C](f: A => C)(implicit functor: BiFunctor[Tuple2]) = functor.first(a)(f)
    def second[D](g: B => D)(implicit functor: BiFunctor[Tuple2]) = functor.second(a)(g)
  }

  val tuple = (0, 0)

  println(tuple.biMap(_ + 4)(_ + 5))
  println(tuple.first(_ + 10))
  println(tuple.second(_ + 15))
}