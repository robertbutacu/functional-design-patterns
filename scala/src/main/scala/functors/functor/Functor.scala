package functors.functor

trait Functor[A] {
  def fmap[B](f: A => B): B
}
