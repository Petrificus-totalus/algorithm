function Node(val) {
  // 节点
  this.val = val; // 节点内数据
  this.next = null; // 节点后继
}

function LList() {
  this.head = new Node("head");
  this.find = find;
  this.insert = insert;
  this.show = show;
  this.previous = previous;
  this.remove = remove;
}

/**
 * 返回一个节点，不存在返回 null
 */
function find(item) {
  let currNode = this.head;
  while (currNode.val !== item) {
    currNode = currNode.next;
  }
  return currNode;
}

/**
 * item节点后插入newElement
 */
function insert(newElement, item) {
  let node = this.find(item);
  if (node === null) {
    throw new Error(`${item}节点不存在`);
  }
  let newNode = new Node(newElement);
  newNode.next = node.next;
  node.next = newNode;
}

function show() {
  let cur = this.head.next;
  while (cur !== null) {
    console.log(cur.val);
    cur = cur.next;
  }
}

function previous(item) {
  let cur = this.head;
  while (cur.next.val !== item) {
    cur = cur.next;
  }
  return cur;
}

function remove(item) {
  let preNode = this.previous(item); // 找到前驱
  let currNode = this.find(item);
  if (preNode) {
    preNode.next = currNode.next;
    currNode.next = null;
  }
}

let l = new LList();
l.insert("one", "head");
l.insert("two", "one");
l.insert("three", "two");
l.show();
l.remove("two");
l.show();
