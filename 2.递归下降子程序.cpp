#include<iostream>
#include<string>
using namespace std;

string a = "";//�����ַ��� 
int adv = 0;//�ַ���С�꣬��ʾʹIPָ����һ�������
void E();
void E1();
void T();
void T1();
void F();

//����E->TE'
void E(){
	cout << "E->TE" << endl;
	T();
	E1();
}

//����E'->+TE'|��
void E1(){
	if(a[adv] == '+'){
			cout << "E->+TE'" << endl;
			adv++;
			T();
			E1();
	}else{
		cout << "E->��" << endl;
	}
} 

//����T->ET' 
void T(){
	cout << "T->FT'" << endl;
	F();
	T1();
}

//����T'->*FT' 
void T1(){
	if(a[adv] == '*'){
		cout << "T'->FE'" << endl;
		adv++;
		F();
		T1();
	}else{
		cout <<"T'->��" << endl;
	}
}

//����F->(E)|i 
void F(){
	if(a[adv] == '('){
		adv++;
		E();
		if(a[adv] == ')'){
			cout << "F->(E)" << endl;
			adv++;
		}else{
			cout << "err!" << endl;
			exit(0);
		}
	}else if(a[adv] == 'i'){
		cout << "E->i" << endl;
		adv++; 
	}else{
		cout << "err!" << endl;
		exit(0);
	}
}

int main(void){
	cin >> a; "�����ַ���"
	E(); "��ڳ���"
	if(a[adv] == '#'){"���ƥ�䵽������,˵��ƥ��ɹ�"
		cout << a << " is a match! " << endl;
	}else{
		cout << " err! " << endl;
	}
	return 0;
}
