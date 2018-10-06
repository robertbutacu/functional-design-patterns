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

object BiFunctor {
  implicit def eitherBiFunctor: BiFunctor[Either] = new BiFunctor[Either] {
    override def biMap[A, B, C, D](input: Either[A, B])(f: A => C)(g: B => D): Either[C, D] = {
      input.left.map(f).right.map(g)
    }

    override def first[A, C](input: Either[A, _])(f: A => C): Either[C, _] =
      input.left.map(f)

    override def second[B, D](input: Either[_, B])(g: B => D): Either[_, D] =
      input.right.map(g)
  }
}