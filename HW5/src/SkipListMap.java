import java.util.ArrayList;
import java.util.List;

public class SkipListMap {
	List<DoublyLinkedList<Unit>> lList;//list of DoublyLinkedList, to present the hierarchical structure of skiplist
	FakeRandomHeight fakeRandomHeight;
	int num;//for count
	public SkipListMap() {
		lList = new ArrayList<DoublyLinkedList<Unit>>();
		for(int i = 0;i<5;i++){
			DoublyLinkedList<Unit>list = new DoublyLinkedList<Unit>();
			lList.add(list);
		}
		fakeRandomHeight = new FakeRandomHeight();
		num = 0;
	}

	public Unit get(String time){
		return getNode(time).getElement();
	}
	private DoublyLinkedList.Node<Unit> getNode(String time){
		int level = lList.size() - 1;
		while(lList.get(level).size() == 0){
			level--;
		}
		DoublyLinkedList.Node<Unit> n = lList.get(level).firstNode();
		while(level >= 0 && n != null){
			DoublyLinkedList<Unit> dll = lList.get(level);			
			while(n != dll.lastNode().getNext() && n.getElement().time.compareToIgnoreCase(time) < 0){
				n = n.getNext();
			}
			
			if(n.getElement() != null && n.getElement().time.compareToIgnoreCase(time) == 0)
			{
				while(n.getDown() != null)
					n = n.getDown();
				return n;
			}
			n = n.getPrev();
			if(n == dll.firstNode().getPrev() && level != 0){
				n = lList.get(level - 1).firstNode();
			}
			else if(level != 0)
				n = n.getDown();
			level--;
		}
		return n;
	}
	public void put(String time, Unit unit){
		int height = fakeRandomHeight.get();
		if(num == 0){
			lList.get(0).addFirst(unit);
			num++;
			return;
		}
		DoublyLinkedList.Node<Unit> nUP = null;
		while(height > lList.size() - 1)
			{
				lList.add(new DoublyLinkedList<Unit>());
			}
		DoublyLinkedList.Node<Unit> nTemp = lList.get(height).firstNode();
		DoublyLinkedList.Node<Unit> nHigherNode = null;
		for(int i = height;i>=0;i--){
			DoublyLinkedList<Unit> dll = lList.get(i);
			nTemp = dll.firstNode();
			if(nTemp == null){
				dll.addFirst(unit);
				dll.firstNode().setUp(nHigherNode);
				if(nHigherNode != null){
					nHigherNode.setDown(dll.firstNode());
				}
				nHigherNode = dll.firstNode();
				continue;
			}
			if(nUP != null)
				nTemp = nUP.getDown();
			else{
				nTemp = dll.firstNode();
			}
			DoublyLinkedList.Node<Unit> last = nTemp;
			while(nTemp != null && nTemp.getElement() != null && nTemp.getElement().time.compareToIgnoreCase(time)<0){
				last = nTemp;
				nTemp = nTemp.getNext();
			}
			nTemp = last;
			nTemp.setUp(nUP);
			if(nUP!=null){
				nUP.setDown(nTemp);
			}
			nUP = nTemp;
			if(nTemp.getElement().time.compareToIgnoreCase(time) < 0){
				DoublyLinkedList.Node<Unit> node = dll.addAfter(unit, nTemp);
				node.setUp(nHigherNode);
				if(nHigherNode != null){
					nHigherNode.setDown(node);
				}
				nHigherNode = node;
			}
			else{
				DoublyLinkedList.Node<Unit> node = dll.addBefore(unit, nTemp);
				node.setUp(nHigherNode);
				if(nHigherNode != null){
					nHigherNode.setDown(node);
				}
				nHigherNode = node;
			}
		}
		num ++;
	}
	public boolean remove(String time){
		Unit unit = get(time);
		if(unit.time.compareToIgnoreCase(time) != 0){
			return false;
		}
		else{
			DoublyLinkedList.Node<Unit> node = getNode(time);
			int level = 0;
			while(node != null){
				Unit unit2 = lList.get(level).remove(node);
				level++;
				node = node.getUp();
			}
			num--;
		}
		return true;
	}
	public List<Unit> subMap(String s1, String s2){
		DoublyLinkedList.Node<Unit> node1 = getNode(s1);

		DoublyLinkedList.Node<Unit> node2 = getNode(s2);
		if(node1 == node2)
			return new ArrayList<Unit>();
		List<Unit>uList = new ArrayList<Unit>();
		if(node1.getElement() != null && node1.getElement().time.compareToIgnoreCase(s1) == 0)
			uList.add(node1.getElement());
		node1 = node1.getNext();
		while(node1 != node2){
			if(node1.getElement() != null)
				uList.add(node1.getElement());
			node1 = node1.getNext();
		}
		uList.add(node2.getElement());
		return uList;
	}
}
