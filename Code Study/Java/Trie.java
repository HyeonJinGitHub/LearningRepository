package com;

import java.util.HashMap;
import java.util.Map;

/** 자식노드맵과 현재 노드가 마지막 글자인지 여부 */
class TrieNode{
	/** 자식 노드 맵 */
	private Map<Character, TrieNode> childNodes = new HashMap<>();
	/** 마지막 글자인지 여부 */
	private boolean isLastChar;
	
	public TrieNode() {
		// TODO Auto-generated constructor stub
	}
	
	public Map<Character, TrieNode> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(Map<Character, TrieNode> childNodes) {
		this.childNodes = childNodes;
	}
	public boolean isLastChar() {
		return isLastChar;
	}
	public void setLastChar(boolean isLastChar) {
		this.isLastChar = isLastChar;
	}
	
}
/** 루트 노드가 저장됨 */
class Trie{
	/** 루트 노드 */
	private TrieNode rootNode;
	
	/** 생성자 */
	public Trie() {
		rootNode = new TrieNode();
	}
	
	/** 입력받은 단어의 각 알파벳을 계층구조의 자식노드로 만들어 넣는다.
	 * 해당 계층 문자의 자식노드가 존재하지 않을 때에만 자식 노드를 생성(람다식) */
	public void insert(String word) {
		TrieNode thisNode = this.rootNode;
		
		for(int i=0; i<word.length(); ++i) {
			thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c->new TrieNode());
		}
		
		thisNode.setLastChar(true); //여기까지를 끝으로 하는 단어가 존재한다는 표시
	}
	
	/** 특정 단어가 Trie에 존재하는지를 확인하기 위한 함수
	 * 조건 1. 루트 노드부터 순서대로 알파벳이 일치하는 자식노드들이 존재할 것
	 * 조건 2. 해당 단어의 마지막 글자에 해당하는 노드의 isLastChar가 true일 것 (해당 글자를 마지막으로 하는 단어가 있다는 뜻)
	 */
	public boolean contains(String word) {
		TrieNode thisNode = this.rootNode;
		
		for(int i=0; i<word.length(); ++i) {
			char ch = word.charAt(i);
			TrieNode node = thisNode.getChildNodes().get(ch);
			
			if(node==null)
				return false;
			
			thisNode = node;
		}
		
		return thisNode.isLastChar();
	}
	
	/** Trie에 넣었던 단어 삭제하는 과정
	 * contains 메서드처럼 주어진 단어를 찾아 하위 노드로 단어 길이만큼 내려감.
	 * 하위 노드로 내려가며 삭제 대상 단어 탐색 -> 다시 올라오며 삭제하는 구조 (부모 노드의 정보가 없기 때문) 
	 * 탐색 진행 방향 : 부모 노드 -> 자식 노드
	 * 
	 * 삭제 조건 1. 자식 노드를 가지고 있지 않아햐 함.
	 * 삭제 조건 2. 삭제를 시작하는 첫 노드는 isLastChar==true여야 함. --> false면 Trie에 없다는 뜻
	 * 삭제 조건 3. 삭제 진행 중에는 isLastChar==false 여야 함. --> 삭제 과정 중 isLastChar가 true라는 건 또 다른 단어가 있다는 의미, 삭제 대상이 아님
	 */
	public void delete(String word) {
		delete(this.rootNode, word, 0); //최초로 delete버리는부분
	}

	public void delete(TrieNode thisNode, String word, int idx) {
		char ch = word.charAt(idx);
		
		//아예 없는 단어인 경우 안지움
		if(!thisNode.getChildNodes().containsKey(ch)) {
			System.out.println(word+"라는 것이 트라이에 없습니당.");
			return;
		}
		
		TrieNode childNode = thisNode.getChildNodes().get(ch);
		idx += 1;
		
		if(idx == word.length()) {
			//삭제 조건 2번 항목 (노드는 존재하지만 insert한 단어가 아닌 경우)
			if(!childNode.isLastChar()) {
				System.out.println(word+"라는 것이 트라이에 없습니당.");
				return;
			}
			
			//삭제 조건 1번 항목(삭제 대상언어의 제일 끝으로, 이 단어를 포함하는 더 긴 단어가 없으면 삭제 시작)
			if(childNode.getChildNodes().isEmpty())
				thisNode.getChildNodes().remove(ch);
		}else {
			delete(childNode, word, idx); //콜백 함수 부분
			//삭제 조건 1, 3번 항목
			//삭제 중, 자식 노드가 없고 현재 노드로 끝나는 다른 단어가 없는 경우 이 노드 삭제
			if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty()) {
				thisNode.getChildNodes().remove(ch);
			}
		}
		
	}
	
	boolean isRootEmpty() {
		return this.rootNode.getChildNodes().isEmpty();
	}
	
}
public class Main {
	public static void main(String[] args) {
		Trie trie = new Trie();
		
		//insert
		
		trie.insert("PI");
		trie.insert("PIE");
		trie.insert("POW");
		trie.insert("POP");
		
		
		//Contains
		System.out.println(trie.contains("POW")?"있음":"없음");
		System.out.println(trie.contains("PIES")?"있음":"없음");
		
		
		//DELETE
		trie.delete("POP");
		System.out.println(trie.contains("POP")?"있음":"없음");
		System.out.println(trie.contains("POW")?"있음":"없음");
		
		//에러 예
//		trie.delete("PO");
//		trie.delete("PIES");
//		trie.delete("PEN");
	}
}//end of class
