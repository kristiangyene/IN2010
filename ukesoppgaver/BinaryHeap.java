//Fint å kunne til eksamen

//Bubble up
//If inserted element is smaller than its parent node in case of Min-Heap OR
//greater than its parent node in case of Max-Heap, swap the element with its parent.
//Keep repeating the above step, if node reaches its correct position, STOP.
/*
 * Performs the "bubble up" operation to place a newly inserted element
 * (i.e. the element that is at the size index) in its correct place so
 * that the heap maintains the min-heap order property.
 */
  protected void bubbleUp() {
      int index = this.size;

      while (hasParent(index)
              && (parent(index).compareTo(array[index]) > 0)) {
          // parent/child are out of order; swap them
          swap(index, parentIndex(index));
          index = parentIndex(index);
      }
  }

//RemoveMin
//Take out the element from the root.( it will be minimum in case of Min-Heap and
//maximum in case of Max-Heap). Take out the last element from the last level from
//the heap and replace the root with the element. Perform bubble-Down. All delete
//operation must perform bubble-Down Operation.
/*
* Removes and returns the minimum element in the heap.
*/
  public T remove() {
  	// what do want return?
  	T result = peek();

  	// get rid of the last leaf/decrement
  	array[1] = array[size];
  	array[size] = null;
  	size--;

  	bubbleDown();

  	return result;
  }



//Bubble down
//If replaced element is greater than any of its child node in case of Min-Heap OR
//smaller than any if its child node in case of Max-Heap, swap the element with its
//smallest child(Min-Heap) or with its greatest child(Max-Heap).
//Keep repeating the above step, if node reaches its correct position, STOP.
/*
 * Performs the "bubble down" operation to place the element that is at the
 * root of the heap in its correct place so that the heap maintains the
 * min-heap order property.
 */
  protected void bubbleDown() {
      int index = 1;

      // bubble down
      while (hasLeftChild(index)) {
          // which of my children is smaller?
          int smallerChild = leftIndex(index);

          // bubble with the smaller child, if I have a smaller child
          if (hasRightChild(index)
              && array[leftIndex(index)].compareTo(array[rightIndex(index)]) > 0) {
              smallerChild = rightIndex(index);
          }

          if (array[index].compareTo(array[smallerChild]) > 0) {
              swap(index, smallerChild);
          } else {
              // otherwise, get outta here!
              break;
          }

          // make sure to update loop counter/index of where last el is put
          index = smallerChild;
      }
  }
