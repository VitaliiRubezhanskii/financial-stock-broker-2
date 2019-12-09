rs.initiate( {
    _id : "rs0",
    members: [
        { _id: 0, host: "mongo1:27017" },
        { _id: 1, host: "mongo2:27017" },
        { _id: 2, host: "mongo3:27017" }
    ]
});

cfg = rs.conf()
cfg.members[0].priority = 5
cfg.members[1].priority = 5
cfg.members[2].priority = 50
rs.reconfig(cfg)