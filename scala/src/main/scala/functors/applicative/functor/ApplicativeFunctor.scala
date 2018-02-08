package functors.applicative.functor

/**
  * Applicative is an intermediate between functor and monad.
  */

trait ApplicativeFunctor[F[_]] {
  def apply[A, B](f: F[A => B]): F[A] => F[B]
}
