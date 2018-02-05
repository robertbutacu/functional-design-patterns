package monads.monad

trait Monad[M[_]] {
  def map[S, T](f: T => S): M[S]
  def flatMap[T,S](f: T => M[S]): M[S]
}
