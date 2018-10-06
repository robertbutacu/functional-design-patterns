package functors.functor

import scala.language.higherKinds

/**
  * ADT which aspires to be a functor must preserve 2 laws:
  *   1. fmap over the identity functor of the ADT should coincide with the original ADT
  *     fmap(identity, adt) === adt
  *
  *   2. The composition of 2 functions and mapping the resulting function over a functor
  *     should be the same as mapping the first function over the functor and then the second one.
  *     fmap(f compose g, adt) === fmap(f, fmap(g, adt))
  *
  * Some functors include: List, Set, Map, Option, etc.
  */
trait Functor[F[_]] {
  def fmap[A, B](fA: F[A])(f: A => B): F[B]
}

object Functor extends App {
  implicit def listFunctor: Functor[List] = new Functor[List] {
    override def fmap[A, B](fA: List[A])(f: A => B): List[B] =
      fA.map(f)
  }

  implicit def optionFunctor: Functor[Option] = new Functor[Option] {
    override def fmap[A, B](fA: Option[A])(f: A => B): Option[B] = fA.map(f)
  }

  def transformList[F[_], A, B](list: F[A], f: A => B)(implicit functor: Functor[F]): F[B] = {
    functor.fmap(list)(f)
  }

  println(transformList(List(1,2,3,4), (x: Int) => x + 1))
  println(transformList(Option(4), (x: Int) => x + 1))
}