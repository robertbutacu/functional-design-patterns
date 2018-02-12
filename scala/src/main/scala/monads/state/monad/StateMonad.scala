package monads.state.monad


/**
  * The equivalent of Memento design pattern in OOP on steroids,
  *   state monads deal with maintaining previous states WHILE generating new ones.
 */

trait StateMonad[T, +A] {
  def apply[S, A](f: S => (S, A)): StateMonad[S, A]
}
