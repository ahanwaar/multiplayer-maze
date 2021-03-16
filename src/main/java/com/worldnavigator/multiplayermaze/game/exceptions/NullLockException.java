package com.worldnavigator.multiplayermaze.game.exceptions;

public class NullLockException extends NullPointerException {

  public NullLockException() {
    super("This object doesn't have a lock!");
  }
}
