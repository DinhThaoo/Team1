
from py_rete.production import Production
from py_rete.conditions import Cond
from py_rete.conditions import AND
from py_rete.conditions import Filter
from py_rete.conditions import Bind
from py_rete.network import ReteNetwork
from py_rete.common import WME
from py_rete.common import V
from py_rete.fact import Fact

net = ReteNetwork()
c0 = Cond('f-0','sodon',V('x'))
c1 = Cond('f-2','rank','tapsu')
c2 = Cond('f-2','rank','vip')
c3= Cond('f-2','khachquen','true')
c4 = Cond('f-1','sokhachquen',V('c'))
f0 = Filter(lambda x: x < 6)
f1 = Filter(lambda x: x >= 6)
f2 = Filter(lambda c: c <=3)
f3 = Filter(lambda c: c >3)

@Production(c0 & f1)
def p1(net):
    f['rank']="vip"
    net.update_fact(f)
    print("Rank VIP")

@Production(AND(c0, f0))
def p0(net):
    f['rank']="tapsu"
    net.update_fact(f)
    print("Rank tap su")

@Production( c4 & f2)
def p2(net):
    f['sokhachquen']+=3
    net.update_fact(f)
    print("duoc thuong 3 khach")

@Production( c4 & f3)
def p3(net):
    f['sokhachquen']+=4
    net.update_fact(f)
    print("duoc thuong 4 khach")

@Production(c1 & c3)
def p5(net):
    f['sodon']+=1
    net.update_fact(f)
    print("cho khach quen dc them 1 don")
    
@Production(c3 & c2)
def p4(net):
    f['sodon']+=2
    net.update_fact(f)
    print("cho khach quen dc them 2 don")

net.add_production(p0)
net.add_production(p1)
net.add_production(p2)
net.add_production(p3)
net.add_production(p4)
net.add_production(p5)
f=Fact(sodon=1,khachquen='true', sokhachquen=1)

net.add_fact(f)

am0 = net.build_or_share_alpha_memory(c0)
print(f)
net.run(6)
print(f)

print("so tien nhan dc la",str(f['sodon']*50000 + f['sokhachquen']*20000))
