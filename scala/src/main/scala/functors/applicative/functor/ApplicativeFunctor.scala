package functors.applicative.functor

/**
  * Applicative is an intermediate between functor and monad.
  *
  * Its importance are dominated by:
  *   - ability to apply functions of more parameters
  *   - can apply functions wrapped into a functor context
  *   - can combine multiple functors into one single product
  */

trait ApplicativeFunctor[F[_]] {
  def apply[A, B](fa: F[A])(f: F[A => B]): F[A] => F[B]
}
