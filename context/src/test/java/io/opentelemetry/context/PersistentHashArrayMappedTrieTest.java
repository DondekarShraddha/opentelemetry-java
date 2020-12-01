/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.context;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PersistentHashArrayMappedTrieTest {

  @Test
  void hashCollisions() {
    HashCollidingKey cheese = new HashCollidingKey();
    HashCollidingKey wine = new HashCollidingKey();
    PersistentHashArrayMappedTrie.Node root =
        PersistentHashArrayMappedTrie.put(null, cheese, "cheddar");

    PersistentHashArrayMappedTrie.Node child =
        PersistentHashArrayMappedTrie.put(root, wine, "Pinot Noir");

    assertThat(PersistentHashArrayMappedTrie.get(child, cheese)).isEqualTo("cheddar");
    assertThat(PersistentHashArrayMappedTrie.get(child, wine)).isEqualTo("Pinot Noir");
  }

  private static class HashCollidingKey implements ContextKey<String> {
    @Override
    public int hashCode() {
      return 1;
    }
  }
}
