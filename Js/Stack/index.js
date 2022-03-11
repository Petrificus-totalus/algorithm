function Stack(maxSize) {
  this.data = [];
  this.maxSize = maxSize;
  this.push = push;
  this.pop = pop;
  this.peek = peek;
  this.clear = clear;
  this.top = 0;
  this.length = length;
}

function push(val) {
  if (this.length === this.maxSize) {
    throw new Error("栈满了");
  }
  this.data[this.top++] = val;
}

function pop() {
  if (this.length() === 0) {
    throw new Error("栈空");
  }
  return this.data[--this.top];
}

function peek() {
  if (this.length() === 0) {
    throw new Error("栈空");
  }
  return this.data[this.top - 1];
}

function clear() {
  this.top = 0;
}

function length() {
  return this.top;
}

/**
 * 判断回文字符串
 * @param {*} word
 */
function isPalindrome(word) {
  let s = new Stack();
  for (let item of word) {
    s.push(item);
  }
  let newWord = "";
  while (s.length() > 0) {
    newWord += s.pop();
  }
  return newWord === word;
}

console.log(isPalindrome("racecar"));

/**
 * 十进制转二进制
 * @param {*} num
 */
function trans(num) {
  let s = new Stack();
  while (num > 0) {
    s.push(num % 2);
    num = Math.floor(num / 2);
  }
  let result = "";
  while (s.length() > 0) {
    result += s.pop();
  }
  return result;
}
