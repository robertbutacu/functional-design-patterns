package functors.functor

abstract class List[A] extends Functor[A]{
  def head: Option[A]
  def tail: List[A]
}

case object EmptyList extends List[Nothing] {
  override def head: Option[Nothing] = None

  override def tail: EmptyList.type = this

  override def fmap[List](f: Nothing => List) = ???
}

case class AList[A](value: A, aTail: List[A]) extends List {
  override def head: Option[A] = Some(this.value)

  override def tail = ???

  override def fmap[B](f: Nothing => B) = ???
}




