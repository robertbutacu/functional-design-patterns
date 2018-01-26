package functors.functor

case class List[A](h: A, t: List[A]) extends Functor[List]{
  def head: Option[A] = Some(this.h)
  def tail: List[A] = this.t

  override def fmap[A, B](fa: List[A])(f: A => B) = ???
}



