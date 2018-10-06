package monads.monad

import scala.language.higherKinds

trait Monad[M[_]] {
  def pure[A](a: A): M[A]
  def flatMap[A, B](input: M[A])(f: A => M[B]): M[B]
}

object Monad extends App {
  implicit def optionMonad: Monad[Option] = new Monad[Option] {
    override def pure[A](a: A): Option[A] = Some(a)

    override def flatMap[A, B](input: Option[A])(f: A => Option[B]): Option[B] =
      input.flatMap(f)
  }

  def applyFunction[M[_], A, B](input: M[A], f: A => M[B])(implicit m: Monad[M]): M[B] =
    m.flatMap(input)(f)

  println(applyFunction(Some(50), (x: Int) => if(x > 10) Some(10) else None))
}